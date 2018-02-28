package com.qm.core.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qm.core.config.GlobalConfig;
import com.qm.core.config.SystemConfig;
import com.qm.core.util.FileUtil;
import com.qm.core.util.RequestUtil;
import com.qm.core.util.StringUtil;
import com.qm.core.util.TextFileUtil;
import com.qm.core.util.UUIDUtil;
import com.qm.core.util.ZipFileUtil;
import com.qm.sys.domain.SysUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseContorllers extends ActionSupport implements ServletRequestAware,
ServletResponseAware {
protected Logger logger = LoggerFactory.getLogger(BaseAction.class);

private static final long serialVersionUID = 1L;
/**
* HttpSession对象
*/
protected HttpSession session;
/**
* 当前请求对应的HttpServletRequest对象 该request对象已经在字符编码过滤器ChracterEncoderFilter中
* 设置了编码格式request.setCharacterEncoding("UTF-8");
*/
protected HttpServletRequest request;
/**
* 当前请求对应的HttpServletResponse对象
*/
protected HttpServletResponse response;
/**
* Web系统对应的全局Scope ServletContext对象
*/
protected ServletContext application;
/**
* 系统列表分页开始位置
*/
protected Integer start = 0;
/**
* 系统列表分页限制大小
*/
protected Integer limit = 30;
/**
* 系统列表分页列表结果记录总数
*/
protected Long total = 0L;
/**
* 排序字段名称
*/
protected String sort;
/**
* 排序方向（asc,desc）
*/
protected String dir;
/**
* extjs 接收的判断结果是否正确的标志参数
*/
public boolean success = true;
/**
* 列表数据
*/
protected List<Map<String, Object>> rows;
/**
* 返回状态,1 操作成功,2 未登录系统,3 操作异常 由全局对象GlobalConfig指定
*/
public int state = 1;
/**
* 操作结果提示信息(未空则提示默认信息)
*/
protected String msg;
/**
* 当前登录系统用户
*/
protected   SysUser sysUser;
/**
* 初始化Action时获取相关参数
*/
@Override
public void setServletRequest(HttpServletRequest request) {
this.request = request;
this.session = this.request.getSession();
this.application = this.session.getServletContext();
sysUser = (SysUser) request.getSession().getAttribute(
		GlobalConfig.LONGIN_USER_SESSION_KEY);
if (sysUser != null) {
	limit = sysUser.getPagingSize() < 2 ? 2 : sysUser.getPagingSize();
}
init();
}

@Override
public void setServletResponse(HttpServletResponse response) {
this.response = response;
}

/**
* 往界面输出json格式数据
* 
* @param object
*            如果为字符串则直接输出，如果为对象列表或json对象，则序列号json格式后输出
* @throws Exception
*/
public void writeJson(Object object) throws Exception {
response.reset();
response.setContentType("application/json;charset=UTF-8");
response.setCharacterEncoding("utf-8");
response.getWriter().write(toJsonString(object));
response.getWriter().flush();
}

public void writeJson(String prefex,Object object) throws Exception {
response.reset();
response.setContentType("application/json;charset=UTF-8");
response.setCharacterEncoding("utf-8");
String msg="";
if(this.msg!=null){
	msg=this.msg.replaceAll("\"","\\\"");
}
String json="{\"state\":"+this.state+",\"msg\":\""+msg+"\",\""
		+prefex+"\":"+toJsonString(object)+"}";
response.getWriter().write(json);
response.getWriter().flush();
}

public String toJsonString(Object object){
if (String.class.equals(object.getClass())) {
	return object.toString();
} else if ((ArrayList.class.equals(object.getClass()))
		|| (List.class.equals(object.getClass()))) {
	JsonConfig config = new JsonConfig();  
    config.registerJsonValueProcessor(Date.class, new DateJsonProcessor());  
	JSONArray json = JSONArray.fromObject(object,config);
	return json.toString();
} else {
	JsonConfig config = new JsonConfig();  
    config.registerJsonValueProcessor(Date.class, new DateJsonProcessor());  
	JSONObject json = JSONObject.fromObject(object,config);
	return json.toString();
}
}

/**
* 下载压缩格式文件
* 
* @param srcFileList
*            要下载的文件
* @param zipFileNameList
*            要下载的文件压缩后的文件名称
* @param mutiFileDownName
*            下载时保存的压缩文件名称
* @param description
*            对下载文件的说明,会建立单独的说明文件，添加到压缩文件中
* @throws Exception
*/
public void downLoadFile(List<File> srcFileList,
	List<String> zipFileNameList, String mutiFileDownName,
	String description) throws Exception {
response.setCharacterEncoding("utf-8");
if (srcFileList.size() > 0) {
	File descFile = null;
	if (StringUtil.isNotBlank(description)) {
		descFile = FileUtil.createFile(SystemConfig
				.getPath("downLoad_zip_path")
				+ UUIDUtil.getUUID()
				+ "/readme.txt");
		TextFileUtil.writeToFile(descFile, description, "utf8");
		srcFileList.add(descFile);
		zipFileNameList.add("说明.txt");
	}
	if (srcFileList.size() == 1) {
		String fileType = FileUtil.getFileType(srcFileList.get(0)
				.getName());
		// 如果下载的文件只有一个压缩文件
		if (".rar".equals(fileType) || ".zip".equals(fileType)) {
			String downLoadName = FileUtil.modifyFileType(
					zipFileNameList.get(0), fileType);
			downLoadFile(downLoadName, srcFileList.get(0));
		} else {
			String downLoadName = FileUtil.modifyFileType(
					zipFileNameList.get(0), ".zip");
			File zipFile = FileUtil.createFile(SystemConfig
					.getPath("downLoad_zip_path")
					+ UUIDUtil.getUUID()
					+ ".zip");
			ZipFileUtil.compressMutiFile(
					srcFileList.toArray(new File[0]),
					zipFileNameList.toArray(new String[0]), zipFile);// 压缩文件(一个文件也压缩，加快下载速度)
			downLoadFile(downLoadName, zipFile);
			zipFile.delete();
		}
	} else if (srcFileList.size() > 1) {
		File zipFile = FileUtil.createFile(SystemConfig
				.getPath("downLoad_zip_path")
				+ UUIDUtil.getUUID()
				+ ".zip");
		ZipFileUtil.compressMutiFile(srcFileList.toArray(new File[0]),
				zipFileNameList.toArray(new String[0]), zipFile);// 压缩文件
		downLoadFile(mutiFileDownName, zipFile);
		zipFile.delete();
	}
	if (descFile != null && descFile.exists()) {
		descFile.delete();
		descFile.getParentFile().delete();
	}
} else {
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().print(
			"<span style='color:red;'>您所选择的文件不存在，请检查是否已经被删除!</span>");
}
}

/**
* 将单个文件放入下载流
* 
* @param downName
*            下载文件在浏览器中显示的文件名称
* @param srcFile
*            要下载的文件对象
* @throws Exception
*/
public void downLoadFile(String downName, File srcFile) throws Exception {
response.setContentType("application/x-msdownload");
boolean isIE = RequestUtil.getParamBoolean(request, "isIE", true);
if (isIE) {
	downName = java.net.URLEncoder.encode(downName, "UTF-8");
}
downName = new String(downName.getBytes("UTF-8"), "ISO8859-1");
response.setHeader("Content-Disposition", "attachment;filename=\""+ downName + "\"");
javax.servlet.ServletOutputStream out = response.getOutputStream();
InputStream in = new FileInputStream(srcFile);
byte[] b = new byte[1024];
int len = in.read(b);
while (len != -1) {
	out.write(b, 0, len);
	len = in.read(b);
}
out.flush();
in.close();
}

/**
* 将输入流对象，转发下载到浏览器
* 
* @param downName
*            下载文件在浏览器中显示的文件名称
* @param srcIn
*            下载的输入流对象
* @throws Exception
*/
public void downLoadFile(String downName, InputStream srcIn)
	throws Exception {
downName = java.net.URLEncoder.encode(downName, "UTF-8");
response.setContentType("application/x-msdownload");
response.setHeader("Content-Disposition", "attachment;filename=\""
		+ downName + "\"");
javax.servlet.ServletOutputStream out = response.getOutputStream();
byte[] b = new byte[1024];
int len = srcIn.read(b);
while (len != -1) {
	out.write(b, 0, len);
	len = srcIn.read(b);
}
out.flush();
srcIn.close();
}

/**
* 输出数据
* 
* @param msg
* @throws Exception
*/
public void write(String msg) throws Exception {
response.getWriter().print(msg);
response.getWriter().flush();
}

/**
* 输出数据
* 
* @param msg
* @throws Exception
*/
public void writeln(String msg) throws Exception {
response.getWriter().println(msg);
response.getWriter().flush();
}

/**
* 初始化方法 当Action实例化时需要做处理，可以通过覆盖改方法进行处理
*/
public void init() {

}

public Integer getStart() {
return start;
}

public void setStart(Integer start) {
this.start = start;
}

public Integer getLimit() {
return limit;
}

public void setLimit(Integer limit) {
this.limit = limit;
}

public Long getTotal() {
return total;
}

public void setTotal(Long total) {
this.total = total;
}

public boolean isSuccess() {
return success;
}

public void setSuccess(boolean success) {
this.success = success;
}

public void setDir(String dir) {
this.dir = dir;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public String getSort() {
return sort;
}

public void setSort(String sort) {
this.sort = sort;
}

public String getDir() {
return dir;
}

public int getState() {
return state;
}

@Override
@JSON(serialize = false)
public Collection<String> getActionErrors() {
return super.getActionErrors();
}

@Override
@JSON(serialize = false)
@Deprecated
public Collection<String> getActionMessages() {
return super.getActionMessages();
}

@Override
@JSON(serialize = false)
@Deprecated
public Collection<String> getErrorMessages() {
return super.getErrorMessages();
}

@Override
@JSON(serialize = false)
@Deprecated
public Map<String, List<String>> getErrors() {
return super.getErrors();
}

@Override
@JSON(serialize = false)
public Map<String, List<String>> getFieldErrors() {
return super.getFieldErrors();
}

@Override
@JSON(serialize = false)
public Locale getLocale() {
return super.getLocale();
}

@Override
@JSON(serialize = false)
public ResourceBundle getTexts() {
return super.getTexts();
}

@JSON(serialize = false)
public HttpSession getSession() {
return session;
}

public void setSession(HttpSession session) {
this.session = session;
}

@JSON(serialize = false)
public HttpServletRequest getRequest() {
return request;
}

public void setRequest(HttpServletRequest request) {
this.request = request;
}

@JSON(serialize = false)
public HttpServletResponse getResponse() {
return response;
}

public void setResponse(HttpServletResponse response) {
this.response = response;
}

@JSON(serialize = false)
public ServletContext getApplication() {
return application;
}

/**
* 获取分页对象
* 
* @return
*/
@JSON(serialize = false)
public PageInfo getPageInfo() {
PageInfo pageInfo = new PageInfo();
pageInfo.setStart(this.start);
pageInfo.setLimit(this.limit);
pageInfo.setDir(this.dir);
pageInfo.setSort(this.sort);
return pageInfo;
}

public void setApplication(ServletContext application) {
this.application = application;
}

public List<Map<String, Object>> getRows() {
return rows;
}
//根据titleArr，keyNameArr为页面构建选择导出数据格式
protected void _exportFiledList(String[] titleArr) throws Exception {
// 传入导出界面的标志符
StringBuilder sb=new StringBuilder();
sb.append("{rows:[");
//response.reset();
//response.setContentType("application/json;charset=UTF-8");
//response.setCharacterEncoding("utf-8");
for (int i = 0; i < titleArr.length; i++) {
	sb.append("{xtype:'checkbox',");
	sb.append("name:'_exportFiled',");
	sb.append("boxLabel:'"+titleArr[i]+"',");
	sb.append("checked: true,");
	sb.append("inputValue:"+i);
	sb.append("},");
}
if (sb.toString().endsWith(",")) {
	sb.deleteCharAt(sb.length()-1);
}
sb.append("]}");
writeJson(sb.toString());
}

//根据界面选择对应的字段序号，过滤导出字段
protected String[][] _filterExportFileds(String[] titleArr,String[] keyNameArr) {
List<Integer> exportFileds=RequestUtil.getParamIntList(request,"_exportFiled",null);
String[] titleArr1 = new String[exportFileds.size()];
String[] keyNameArr1 = new String[exportFileds.size()];
int i=0;
for(int index:exportFileds){
	titleArr1[i]=titleArr[index];
	keyNameArr1[i]=keyNameArr[index];
	i++;
}
return new String[][] { titleArr1, keyNameArr1 };
}

}

