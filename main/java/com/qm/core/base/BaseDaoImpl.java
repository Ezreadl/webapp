package com.qm.core.base;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;


/**
 * 实现IBaseDao定义的接口方法
 * @author wangchong
 */

public class BaseDaoImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IBaseDao<T,ID>{
//public class BaseDaoImpl<T,ID extends Serializable> {
	
	//jpa实体管理器
    private EntityManager em=null;
    //接口元数据信息
    private RepositoryInformation information=null;
    
    
	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em; 
    }
    
	public void setInformation(RepositoryInformation information) {
		this.information = information;
	}
	
	public void update(T t){
		em.merge(t);
	}
	
	@Override
	public long findDataCount1(Object[] params) throws Exception{
		Class<?> inter=information.getRepositoryInterface();
		Method method=inter.getMethod("findDataCount1",Object[].class);
		String qmSql="";
		boolean nativeQuery=true;
		for(Annotation a:method.getAnnotations()){
			if(a instanceof QmQuery){
				qmSql=((QmQuery) a).value();
				nativeQuery=((QmQuery) a).nativeQuery();
			}
		}
		if(qmSql==null || "".equals(qmSql.trim())){
			throw new RuntimeException("查询语句为空字符串,请检查是否添加QmQuery标注！");
		}
		return findDataCount(qmSql, params, nativeQuery);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findDataList1(Object[] params,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList1", params, pageInfo);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findDataList2(Object[] params,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList2", params, pageInfo);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findDataList3(Object[] params,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList3", params, pageInfo);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findDataList4(Object[] params,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList4", params, pageInfo);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findDataList5(Object[] params,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList5", params, pageInfo);
	}
	
	//通过获取接口类标注sql和nativeQuery,传入参数执行查询列表
	@SuppressWarnings("rawtypes")
	private List findDataList(String interMethodName,Object[] params,PageInfo pageInfo) throws Exception{
		Class<?> inter=information.getRepositoryInterface();
		Method method=inter.getMethod(interMethodName,Object[].class,PageInfo.class);
		String qmSql="";
		boolean nativeQuery=true;
		for(Annotation a:method.getAnnotations()){
			if(a instanceof QmQuery){
				qmSql=((QmQuery) a).value();
				nativeQuery=((QmQuery) a).nativeQuery();
			}
		}
		if(qmSql==null || "".equals(qmSql.trim())){
			throw new RuntimeException("查询语句为空字符串,请检查是否添加QmQuery标注！");
		}
		return findDataList(qmSql, params, pageInfo, nativeQuery);
	}
	
	/**
	 * 通过取接口findDataList1方法的标注方式执行查询，
	 * 标注@QmQuery中的value为查询QmSql语句，
	 * nativeQuery=true表示执行sql（返回List<Map<String,Object>>），否则表示执行hql返回List<entity对象>
	 * QmSql语法见findDataList方法注释
	 */
	@Override
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList1", query, pageInfo);
	}
	
	/**
	 * 通过取接口findDataList2方法的标注方式执行查询，
	 * 标注@QmQuery中的value为查询QmSql语句，
	 * nativeQuery=true表示执行sql（返回List<Map<String,Object>>），否则表示执行hql返回List<entity对象>
	 * QmSql语法见findDataList方法注释
	 */
	@Override
	public GridListData findDataList2(IQuery query,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList2", query, pageInfo);
	}
	
	/**
	 * 通过取接口findDataList3方法的标注方式执行查询，
	 * 标注@QmQuery中的value为查询QmSql语句，
	 * nativeQuery=true表示执行sql（返回List<Map<String,Object>>），否则表示执行hql返回List<entity对象>
	 * QmSql语法见findDataList方法注释
	 */
	@Override
	public GridListData findDataList3(IQuery query,PageInfo pageInfo) throws Exception{
		return findDataList("findDataList3", query, pageInfo);
	}
	
	//通过获取接口类标注sql和nativeQuery,传入参数执行查询分页列表
	private GridListData findDataList(String interMethodName,IQuery query,PageInfo pageInfo) throws Exception{
		Class<?> inter=information.getRepositoryInterface();
		Method method=inter.getMethod(interMethodName,IQuery.class,PageInfo.class);
		String qmSql="";
		boolean nativeQuery=true;
		for(Annotation a:method.getAnnotations()){
			if(a instanceof QmQuery){
				qmSql=((QmQuery) a).value();
				nativeQuery=((QmQuery) a).nativeQuery();
			}
		}
		if(qmSql==null || "".equals(qmSql.trim())){
			throw new RuntimeException("查询语句为空字符串,请检查是否添加MyQuery标注！");
		}
		return findDataList(qmSql, query, pageInfo, nativeQuery);
	}
	
	/**
	 * 通过传入参数执行查询列表
	 * sql表示要执行的hql或sql，由nativeQuery参数决定
	 * params执行查询语句中的参数列表
	 * pageInfo分页信息，如果不分页传入null
	 */
	@SuppressWarnings("rawtypes")
	public List findDataList(String sql,Object[] params,PageInfo pageInfo,boolean nativeQuery){
		String orderby="";
		if(pageInfo!=null && isNotBlank(pageInfo.sort)){
			orderby=" order by "+pageInfo.sort;
			if(isNotBlank(pageInfo.dir)){
				orderby=" order by "+pageInfo.sort+" "+pageInfo.dir;
			}
			sql=sql+orderby;
		}
		if(SystemConfig.getBoolean("QmSql.show_sql_and_param")){
			System.out.println("QmSql_ListSql="+sql);
			System.out.println("QmSql_ListParams="+Arrays.toString(params));
		}
		Query query=null;
		if(nativeQuery){
			query=this.em.createNativeQuery(sql);
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}else{
			query=this.em.createQuery(sql);
		}
		if(params!=null && params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i+1, params[i]);
			}
		}
		if(pageInfo!=null && pageInfo.getStart()!=null){
			query.setFirstResult(pageInfo.start);//设置分页开始行
		}
		if(pageInfo!=null && pageInfo.getLimit()!=null){
			query.setMaxResults(pageInfo.limit);//设置分页大小
		}
		return query.getResultList();
	}
	
	/**
	 * 通过传入参数执行查询条目数
	 * sql表示要执行的hql或sql，由nativeQuery参数决定
	 * params执行查询语句中的参数列表
	 */
	public long findDataCount(String sql,Object[] params,boolean nativeQuery){
		if(!sql.matches("(?i)^ *from")){
			int index=sql.toLowerCase().indexOf("from");
			sql=sql.substring(index);
		}
		sql="select count(*) "+sql;
		if(SystemConfig.getBoolean("QmSql.show_sql_and_param")){
			System.out.println("QmSql_CntSql="+sql);
			System.out.println("QmSql_CntParams="+Arrays.toString(params));
		}
		Query query=null;
		if(nativeQuery){
			query=this.em.createNativeQuery(sql);
		}else{
			query=this.em.createQuery(sql);
		}
		if(params!=null && params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i+1, params[i]);
			}
		}
		return  Long.valueOf(query.getSingleResult().toString());
	}
	
	/**
	 * 通过传入QmSql语句和参数接口查询分页及汇总信息。
	 * 
	 * QmSql语法格式：
	 * 1、条件组合符号：{}，表示{}中的查询条件根据传入参数决定组合关系。如：select * from User u {where u.name='zhangsan' and u.age>2}
	 * 2、条件连接符号：where、and、or或#&,其中#&表示根据组合查询自适应where或and连接符号，如：select * from User u {#& u.name='zhangsan' #& u.age>2}
	 * 3、参数占位符：?或:+占位名称，对于有函数操作的字段只能使用:占位符,如：select * from User u {#& u.name=? #& u.age>:age}
	 * 4、模糊查询：只需在参数占位符前后加%，如：select * from User u {#& u.name lik %? #& u.age>:age}
	 * 5、in查询，只需在in中加入参数占位符，如：select * from User u {#& u.name=? #& u.age in(?)}
	 * 
	 * QmSql查询赋值原则：
	 * 1、查询参数对象必须实现IQuery接口
	 * 2、?取查询语句字段名，:占位符取占位名
	 * 3、查询语句字段与查询参数字段映射关系，不区分大小写，优先匹配带表查询名，再匹配不带表查询名,
	 *	    带表查询名与参数名对应关系为：替换.符号为_，如：a.name替换为a_name,不带表查询名与参数名对应关系为，去掉表别名，如：a.name对应name
	 * 4、对bettween...and情况，或对大于、小于类型的区间查询情况，
	 * 	   1）、对?情况：取映射名称+Start和映射名称+End;
	 *	   2）、对:占位：若占位符相同，取占位名+Start和占位名+End;若占位符不同,直接取占位名称
	 * 5、对于in查询，参数值用,号分割多个值
	 * 
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public GridListData findDataList(String qmSql,IQuery query,PageInfo pageInfo,boolean nativeQuery) throws Exception{
		Map<String,Object> paramMap=getQueryNameValueMap(query);//获取查询参数表
		qmSql=formartQmSql(qmSql);//格式化Sql
		List<QueryRelation> paramsList=new ArrayList<QueryRelation>();
		//根据传入参数拼接sql语句，并输出查询条件
		String sql=montageSearchSql(qmSql, paramMap, paramsList);
		//整理查询参数
		List<Object> queryParams=new ArrayList<Object>();
		for(int i=0;i<paramsList.size();i++){
			QueryRelation ret=paramsList.get(i);
			if(ret.paramCnt==1){
				queryParams.add(ret.paramValue);
			}else{
				for(String s:ret.paramValue.toString().split(",")){
					queryParams.add(s);
				}
			}
		}
		//执行查询，如果要求查询记录总数，先查总数，总数大于0查询明细。如果不要求查总数，直接查询明细
		List dataList=null;
		long total=-1;
		Object[] params=queryParams.toArray(new Object[0]);
		if(pageInfo!=null && pageInfo.isFindTotal()){//要求查询总数
			total=findDataCount(sql,params,nativeQuery);
			if(total>0){
				dataList=findDataList(sql,params,pageInfo,nativeQuery);
			}else{
				dataList=new ArrayList();
			}
		}else{//不要求查询总数
			dataList=findDataList(sql,params,pageInfo,nativeQuery);
		}
		return new GridListData(total,dataList);
	}
	
	/*public static void main(String[] args) throws Exception{
		BaseDaoImpl dao=new BaseDaoImpl();
		EquipmentQuery query=new EquipmentQuery();
		query.setIpAddress("192.169.1.1");
		query.setStateFlg(0);
		//query.setKeyWord("省干高升");
		Map<String,Object> paramMap=dao.getQueryNameValueMap(query);//获取查询参数表
		String qmSql1="select * from equipment {#& ( equipName like %:keyWord% or chineseName like %:keyWord% or otherName like %:keyWord% ) #& delFlg=? and stateFlg=? #& ipAddress like %?%}" ;
		String qmSql=dao.formartQmSql(qmSql1);//格式化Sql
		System.out.println("qmSql="+qmSql);
		List paramsList=new ArrayList();
		//根据传入参数拼接sql语句，并输出查询条件
		String sql=dao.montageSearchSql(qmSql, paramMap, paramsList);
		System.out.println(sql);
		System.out.println("paramsList="+paramsList);
	}*/
	
	/**
	 * 执行sql语句或hql语句,根据nativeQuery区分。
	 * params为传入参数列表
	 */
	public int execute(String sql,Object[] params,boolean nativeQuery) throws Exception{
		Query query=null;
		if(nativeQuery){
			query=this.em.createNativeQuery(sql);
		}else{
			query=this.em.createQuery(sql);
		}
		if(params!=null && params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}
	
	/**
	 * 批量更新
	 * @param list
	 */
	public void batchUpdate(List<T> list) {
	     for (int i = 0; i < list.size(); i++) {
	         em.merge(list.get(i));
	         if (i % 500 == 0) {
	             em.flush();
	             em.clear();
	             System.out.println("batchUpdate"+i);
	         }
	     }
	 }
	
	/**
	 * 批量保存
	 * @param list
	 */
	 public void batchSave(List<T> list) {
	     for (int i = 0; i < list.size(); i++) {
	         em.persist(list.get(i));
	         if (i % 500 == 0) {
	             em.flush();
	             em.clear();
	             System.out.println("batchSave="+i);
	         }
	     }
	 }
	
	//获取sql字段名称
	private String findQueryField(String[] qmSqlArr,int idx){
		if(idx<2){
			return "";
		}
		int pos=idx-2;
		String queryField=qmSqlArr[pos];
		if("like".equalsIgnoreCase(queryField) || "in".equalsIgnoreCase(queryField)){
			queryField=qmSqlArr[--pos];
		}
		if("not".equalsIgnoreCase(queryField)){
			queryField=qmSqlArr[--pos];
		}
		return queryField;
	}
	
	//编译QmSql语句，替换QmSql中的执行参数，传入paramMap参数，将执行参数存入params
	//根据传入参数，拼接查询字符串
	private String montageSearchSql(String qmSql,Map<String,Object> paramMap,List<QueryRelation> params){
		String[] sqlArr=qmSql.split("[ \t]");
		boolean isDynGroup=false;//是否为动态条件分组
		for(int i=1;i<sqlArr.length;i++){//扫描复制赋值条件和组合查询
			String curSec=sqlArr[i];
			if(curSec.contains("{")){
				if(isDynGroup){
					throw new RuntimeException("QmSql格式错误，多个'{'连续出现");
				}
				isDynGroup=true;//分组开始
			}
			if(curSec.contains("}")){
				if(!isDynGroup){
					throw new RuntimeException("QmSql格式错误，多个'}'连续出现");
				}
				isDynGroup=false;//分组结束
			}
			if("&#".equals(curSec)){
				throw new RuntimeException("无法识别的&#符号");
			}
			if("?".equals(curSec) || curSec.contains(":")){//出现赋值条件
				boolean isPosSymbol=curSec.contains(":");//是否为占位符
				String queryField=findQueryField(sqlArr,i);//查找条件字段
				if(isPosSymbol){//如果为占位符,字段取占位符的名字
					queryField=curSec.replace(":","");
				}
				if(isDynGroup){//如果是动态条件，判断是否有值，有值加入查询，无值去掉查询语句
					if("between".equalsIgnoreCase(sqlArr[i-1])){//between...and的情况
						if(i+2 >= sqlArr.length){
							throw new RuntimeException("between...and格式不完整");
						}
						String queryFieldEnd=queryField;
						String startPostfix="";
						String endPostfix="";
						if(sqlArr[i].equals(sqlArr[i+2])){//同为?或占位符相同
							startPostfix="Start";
							endPostfix="End";
						}else{
							queryFieldEnd=sqlArr[i+2].replace(":","");
						}
						QueryRelation retStart=getParamValue(paramMap,queryField,startPostfix);
						QueryRelation retEnd=getParamValue(paramMap,queryFieldEnd,endPostfix);
						if(retStart.paramValue==null || retEnd.paramValue==null){
							//没有该条件的查询参数，通过赋值空字符串，移除查询片段
							replaceSearchCondition(sqlArr,i);
						}else{
							//有条件参数，传入参数列表
							params.add(retStart);
							params.add(retEnd);
						}
						i=i+2;//跳过后面一个and
					}else{
						String postfix="";
						if(isRangeSearch(sqlArr,i)){//如果是范围查询，且同为?或占位符相同，加后缀
							if(sqlArr[i-1].contains(">")){
								postfix="Start";
							}else if(sqlArr[i-1].contains("<")){
								postfix="End";
							}
						}
						QueryRelation ret=getParamValue(paramMap,queryField,postfix);
						if(ret.paramValue==null){
							//通过赋值空字符串，移除查询片段
							replaceSearchCondition(sqlArr,i);
						}else{
							params.add(ret);
						}
					}
				}else{//如果不是动态条件，没有值则抛异常
					if("between".equalsIgnoreCase(sqlArr[i-1])){//between...and的情况
						if(i+2 >= sqlArr.length){
							throw new RuntimeException("between...and格式不完整");
						}
						String queryFieldEnd=queryField;
						String startPostfix="";
						String endPostfix="";
						if(sqlArr[i].equals(sqlArr[i+2])){//同为?或占位符相同
							startPostfix="Start";
							endPostfix="End";
						}else{
							queryFieldEnd=sqlArr[i+2].replace(":","");
						}
						QueryRelation retStart=getParamValue(paramMap,queryField,startPostfix);
						if(retStart.paramValue==null){
							throw new RuntimeException(errorTip(retStart));//固定查询条件必须传入赋值参数
						}else{
							params.add(retStart);
						}
						QueryRelation retEnd=getParamValue(paramMap,queryFieldEnd,endPostfix);
						if(retEnd.paramValue==null){
							throw new RuntimeException(errorTip(retEnd));//固定查询条件必须传入赋值参数
						}else{
							params.add(retEnd);
						}
						i=i+2;//跳过后面一个and
					}else{
						String postfix="";
						if(isRangeSearch(sqlArr,i)){//如果是范围查询，且占位符相同，加后缀
							if(sqlArr[i-1].contains(">")){
								postfix="Start";
							}else if(sqlArr[i-1].contains("<")){
								postfix="End";
							}
						}
						QueryRelation ret=getParamValue(paramMap,queryField,postfix);
						if(ret.paramValue==null){
							throw new RuntimeException(errorTip(ret));//固定查询条件必须传入赋值参数
						}else{
							params.add(ret);
						}
					}
				}
			}
		}
		//替换占位符，合并SQL语句
		String connSymbol="where";//是否为动态条件分组
		int paramIdx=0;
		for(int i=0;i<sqlArr.length;i++){//扫描赋值条件和组合查询
			String curSec=sqlArr[i];
			if(curSec.contains("{")){
				sqlArr[i]="";//去掉动态查询条件分组符号
			}
			if(curSec.contains("}")){
				connSymbol="where";
				sqlArr[i]="";//去掉动态查询条件分组符号
			}
			if("#&".equals(curSec)){
				sqlArr[i]=connSymbol;
				connSymbol="and";
			}
			if(curSec.contains(":")){
				sqlArr[i]="?";//替换占位符为?
			}
			if("?".equals(sqlArr[i])){
				if(i>=1 && "%".equals(sqlArr[i-1])){//前半部分模糊查询
					QueryRelation ret=params.get(paramIdx);
					ret.paramValue="%"+ret.paramValue;
					sqlArr[i-1]="";
				}
				if(i<sqlArr.length-1 && "%".equals(sqlArr[i+1])){//后半部分模糊查询
					QueryRelation ret=params.get(paramIdx);
					ret.paramValue=ret.paramValue+"%";
					sqlArr[i+1]="";
				}
				if(i>=2 && "in".equalsIgnoreCase(sqlArr[i-2]) || i>=1 && "in(".equalsIgnoreCase(sqlArr[i-1])){
					QueryRelation ret=params.get(paramIdx);
					sqlArr[i]=getQmInParam(ret);//根据参数,号分割个数，替换?,?,?
				}
				paramIdx++;
			}
		}
		
		//删除空()和()前的连接符
		for(int i=0;i<sqlArr.length-1;i++){
			boolean find=false;
			if("()".equals(sqlArr[i].trim())){
				sqlArr[i]="";
				find=true;
			}else if("(".equals(sqlArr[i].trim())){
				for(int j=i+1;j<sqlArr.length;j++){
					if(isNotBlank(sqlArr[j])){
						if(")".equals(sqlArr[j])){
							sqlArr[j]="";
							find=true;
						}
						break;
					}
				}
			}
			if(find){
				sqlArr[i]="";
				for(int j=i-1;j>=0;j--){
					String cur=sqlArr[j];
					if(isNotBlank(sqlArr[j])){
						//删除连接符
						if(isLinkFlg(cur)){
							sqlArr[j]="";
						}
						break;
					}
				}
			}
		}
		//拼接执行语句
		StringBuilder sqlSb=new StringBuilder();
		for(int i=0;i<sqlArr.length;i++){
			String curSec=sqlArr[i];
			if(isNotBlank(curSec)){
				sqlSb.append(curSec+" ");
			}
		}
		return sqlSb.toString();
	}
	
	//判断字段是否为范围查询，且占位符相同，用于判断是否加后缀
	//盘点当前位置是否为范围查询，且占位符相同
	private boolean isRangeSearch(String[] qmSqlArr,int idx){
		if(idx<=0){
			throw new RuntimeException("?或:占位符不能在第一个位置");
		}
		boolean less=qmSqlArr[idx-1].contains("<");
		boolean biger=qmSqlArr[idx-1].contains(">");
		if(!less && !biger){
			return false;//自身就不是范围查询
		}
		String queryField=findQueryField(qmSqlArr,idx);
		String arg=qmSqlArr[idx];
		//往前找from，往后找select,如果存在
		for(int i=idx-2;i>0;i--){
			if("?".equals(qmSqlArr[i]) || qmSqlArr[i].contains(":")){
				if((less && qmSqlArr[i-1].contains(">")) || (biger && qmSqlArr[i-1].contains("<"))){//条件相反
					String curQueryField=findQueryField(qmSqlArr,i);
					if(curQueryField.equalsIgnoreCase(queryField) && qmSqlArr[i].equalsIgnoreCase(arg)){//字段相同且参数相同
						return true;
					}
				}
			}
			if("from".equalsIgnoreCase(qmSqlArr[i]) || "where".equalsIgnoreCase(qmSqlArr[i])){
				break;
			}
		}
		for(int i=idx+2;i<qmSqlArr.length;i++){
			if("?".equals(qmSqlArr[i]) || qmSqlArr[i].contains(":")){
				if((less && qmSqlArr[i-1].contains(">")) || (biger && qmSqlArr[i-1].contains("<"))){//条件相反
					String curQueryField=findQueryField(qmSqlArr,i);
					if(curQueryField.equalsIgnoreCase(queryField) && qmSqlArr[i].equalsIgnoreCase(arg)){//字段相同且参数相同
						return true;
					}
				}
			}
			if("from".equalsIgnoreCase(qmSqlArr[i]) || "where".equalsIgnoreCase(qmSqlArr[i])){
				break;
			}
		}
		return false;
	}
	
	//替换将动态查询中没有参数值的语句，替换为空值
	private void replaceSearchCondition(String[] qmSqlArr,int curIdx){
		if(curIdx<=0){
			throw new RuntimeException("?或:占位符不能在第一个位置");
		}
		boolean between="between".equalsIgnoreCase(qmSqlArr[curIdx-1]);
		//往前找到链接符，结束
		int brackCnt=0;//移除的前括号数量
		for(int i=curIdx;i>=0;i--){
			String cur=qmSqlArr[i];
			if("{".equalsIgnoreCase(cur) || "}".equalsIgnoreCase(cur)){
				break;
			}
			if("(".equalsIgnoreCase(cur) && isLinkFlg(findPreFlg(qmSqlArr,i))){//当前未括号且前为连接符
				//如果后面为or去掉
				for(int k=i+1;k<qmSqlArr.length;k++){
					if(isNotBlank(qmSqlArr[k])){
						if("%".equalsIgnoreCase(qmSqlArr[k])){
							qmSqlArr[k]="";
							continue;
						}
						//将后面一个连接符置空
						if(isLinkFlg(qmSqlArr[k])){
							qmSqlArr[k]="";
						}
						break;
					}
				}
				break;
			}
			if(")".equalsIgnoreCase(cur) && isLinkFlg(findNextFlg(qmSqlArr,i))){
				break;
			}
			if(cur.contains("(")){
				brackCnt++;
			}else if(cur.contains(")")){
				brackCnt--;
			}
			qmSqlArr[i]="";
			if(isLinkFlg(cur)) {
				break;
			}
		}
		//往后找到链接符，结束
		int cnt=0;
		for(int i=curIdx;i<qmSqlArr.length;i++){
			String cur=qmSqlArr[i];
			if("{".equalsIgnoreCase(cur) || "}".equalsIgnoreCase(cur)){
				break;
			}
			if(")".equalsIgnoreCase(cur) && brackCnt>0){
				qmSqlArr[i]="";
				continue;
			}
			if(")".equalsIgnoreCase(cur) && isLinkFlg(findNextFlg(qmSqlArr,i))){
				break;
			}
			if(isLinkFlg(cur)) {
				cnt++;
			}
			if((between && cnt>=2) || (!between && cnt>=1)){
				break;
			}
			qmSqlArr[i]="";
		}
	}
	
	//判断是否为连接标记符号
	private boolean isLinkFlg(String flg){
		if("where".equalsIgnoreCase(flg) || "and".equalsIgnoreCase(flg) || "#&".equalsIgnoreCase(flg) || "or".equalsIgnoreCase(flg)) {
			return true;
		}
		return false;
	}
	
	//查找上一个标记
	private String findPreFlg(String[] qmSqlArr,int curIdx){
		for(int i=curIdx-1;i>=0;i--){
			if(qmSqlArr[i]!=null && !"".equals(qmSqlArr[i].trim())){
				return qmSqlArr[i];
			}
		}
		return "";
	}
	
	//查找下一个标记
	private String findNextFlg(String[] qmSqlArr,int curIdx){
		for(int i=curIdx+1;i<qmSqlArr.length;i++){
			if(qmSqlArr[i]!=null && !"".equals(qmSqlArr[i].trim())){
				return qmSqlArr[i];
			}
		}
		return "";
	}
	
	//如果为in查询，根据传入参数值，以','号分割拼接?个数
	private String getQmInParam(QueryRelation ret){
		String[] values=ret.paramValue.toString().split(",");
		ret.paramCnt=values.length;
		StringBuilder sb=new StringBuilder();
		for(int j=0;j<values.length;j++){
			if(j==0){
				sb.append("?");
			}else{
				sb.append(",?");
			}
		}
		return sb.toString();
	}
	
	//返回请求查询参数名称与值对应表(字段名称转换给小写)，按方法获取
	private Map<String,Object> getQueryNameValueMap(IQuery query) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		if(query==null){
			return paramMap;
		}
		Method[] mArr=query.getClass().getMethods();
		for(Method m:mArr){
			String name=m.getName();
			if((name.startsWith("get") || name.startsWith("is")) && m.getParameterTypes().length==0){
				String fName=name.replaceAll("^get|^is","");
				Object value=m.invoke(query);
				paramMap.put(fName.toLowerCase(),value);//转换为小写，即忽略字段大小写
			}
		}
		return paramMap;
	}
	
	//格式化QmSql条件
	private String formartQmSql(String qmSql){
		StringBuilder sb=new StringBuilder(qmSql);
		for(int i=1;i<sb.length()-1;i++){
			char c=sb.charAt(i);
			if(c=='>' || c=='<' || c=='?' || c=='!' || c=='=' || c=='{' || c=='}' || c==':' || c=='%' || c==')'){
				char p=sb.charAt(i-1);
				char e=sb.charAt(i+1);
				if((c==' ' || c=='\t') && (e==' ' || e=='\t')){
					sb.deleteCharAt(i);//去掉多余空白字符
					i--;
				}
				if(p!=' ' && p!='>' && p!='<' && p!='!' && p!='('){
					sb.insert(i,' ');//前面插入空格
					i++;
				}
				if(e!=' ' && c!=':' && e!='>' && e!='<' && e!='!' && e!='='){
					sb.insert(i+1,' ');//后面插入空格
					i++;
				}
			}else if(c=='(' && sb.charAt(i+1)!=' '){
				sb.insert(i+1,' ');//后面插入空格
				i++;
			}
		}
		return sb.toString();
	}
	//根据接口对象参数表，获取指定字段或加后缀的参数值，以对象返回
	private QueryRelation getParamValue(Map<String,Object> paramMap,String queryField,String postfix){
		QueryRelation ret=new QueryRelation();
		ret.queryField=queryField;
		queryField=queryField.replace(":", "");
		if(isNotBlank(postfix)){
			queryField=queryField+postfix;//添加后缀
		}
		ret.paramField1=queryField.replace(".","_").toLowerCase();
		ret.paramValue=paramMap.get(ret.paramField1);
		if(ret.paramValue!=null && "".equals(ret.paramValue)){
			ret.paramValue=null;
		}
		if(paramMap.containsKey(ret.paramField1)){
			//如果有满足格式a_name格式的参数字段
			return ret;
		}
		int idx=queryField.indexOf(".");
		if(idx>=0){
			ret.paramField2=queryField.substring(idx+1).toLowerCase();
			if(ret.paramValue==null){
				ret.paramValue=paramMap.get(ret.paramField2.toString());
				if(ret.paramValue!=null && "".equals(ret.paramValue)){
					ret.paramValue=null;
				}
			}
		}
		return ret;
	}
	
	//QmSql格式错误提示
	private String errorTip(QueryRelation ret){
		if(ret.paramValue==null){
			if(ret.paramField1!=ret.paramField2 && ret.paramField2!=null){
				return "没有"+ret.queryField+"对应的查询参数"+ret.paramField1+"或"+ret.paramField2+"值";
			}else{
				return "没有"+ret.queryField+"对应的查询参数"+ret.paramField1+"值";
			}
		}
		return "";
	}
	
	//判断字符串是否为空
	private boolean isNotBlank(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	//查询条件字段与参数字段映射关系
	class QueryRelation {
		//Sql中的查询字段(如：a.name，或:name)
		public String queryField;
		//映射参数值
		public Object paramValue;
		//根据Sql中的查询字段，转换为参数传递中的字段名1(如：a_name，如果有范围a_nameStart,a_nameEnd)
		public String paramField1;
		//根据Sql中的查询字段，转换为参数传递中的字段名2(如：name，如果有范围a_nameStart,a_nameEnd)
		public String paramField2;
		//参数个数
		public int paramCnt=1;
		
		@Override
		public String toString() {
			return "QueryRelation [queryField=" + queryField + ", paramValue=" + paramValue + ", paramField1="
					+ paramField1 + ", paramField2=" + paramField2 + ", paramCnt=" + paramCnt + "]";
		}
		
	}
	/**
	 * 通过取接口findDataListByMap方法的标注方式执行查询，
	 * 
	 * */
	@Override
	public GridListData findDataListByMap(HashMap map, PageInfo pageInfo) throws Exception {
		
		return null;
	}
	

}