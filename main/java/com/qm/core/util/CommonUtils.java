package com.qm.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import exception.BizException;

public class CommonUtils
{
  private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
  
  public static boolean isEmpty(Object obj)
  {
    if (obj == null) {
      return true;
    }
    if ((obj instanceof CharSequence)) {
      return ((CharSequence)obj).length() == 0;
    }
    if ((obj instanceof Collection)) {
      return ((Collection)obj).isEmpty();
    }
    if ((obj instanceof Map)) {
      return ((Map)obj).isEmpty();
    }
    if ((obj instanceof Object[]))
    {
      Object[] object = (Object[])obj;
      if (object.length == 0) {
        return true;
      }
      boolean empty = true;
      for (int i = 0; i < object.length; i++) {
        if (!isEmpty(object[i]))
        {
          empty = false;
          break;
        }
      }
      return empty;
    }
    return false;
  }
  
  public static boolean isEmpty(Object... objects)
  {
    for (Object o : objects) {
      if (isEmpty(o)) {
        return true;
      }
    }
    return false;
  }
  
  public static String uriBuilder(String uri, String paramStr)
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append(uri);
    if (uri.contains("?")) {
      buffer.append("&").append(paramStr);
    } else {
      buffer.append("?").append(paramStr);
    }
    logger.info("uri build :{}", buffer.toString());
    return buffer.toString();
  }
  
  public static String getServiceUrl(HttpServletRequest request, HttpServletResponse response, String uri)
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append(request.isSecure() ? "https://" : "http://");
    buffer.append(request.getServerName());
    buffer.append(uri);
    logger.info("serviceUrl before encode:{}", buffer.toString());
    return response.encodeURL(buffer.toString());
  }
  
  public static String fullUrlBuild(HttpServletRequest request)
  {
    String contextPath = request.getContextPath();
    String uri = request.getRequestURI();
    StringBuffer buffer = new StringBuffer();
    buffer.append(request.getScheme()).append("://").append(request.getServerName())
    
      .append(contextPath).append(uri);
    String queryStr = request.getQueryString();
    if (!isEmpty(queryStr)) {
      buffer.append("?").append(queryStr);
    }
    String fullUrl = buffer.toString();
    if (logger.isDebugEnabled()) {
      logger.info("full url build before encode : {}", fullUrl);
    }
    return fullUrl;
  }
  
  public static Timestamp getTimestamp()
  {
    return new Timestamp(new Date().getTime());
  }
  
  public static Timestamp getMorningTs()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(11, 0);
    cal.set(13, 0);
    cal.set(12, 0);
    cal.set(14, 0);
    return new Timestamp(cal.getTimeInMillis());
  }
  
  public static Timestamp dateToToday(Timestamp timestamp)
  {
    Timestamp currentTime = getTimestamp();
    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd ");
    DateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
    Timestamp time = Timestamp.valueOf(sdf1.format(currentTime) + sdf2.format(timestamp));
    return time;
  }
  
  public static String formatTimeStamp(String format, Timestamp timestamp)
  {
    DateFormat sdf1 = new SimpleDateFormat(format);
    return sdf1.format(timestamp);
  }
  
  public static Timestamp getTodayStartTimeStamp()
  {
    Timestamp currentTime = getTimestamp();
    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    Timestamp time = Timestamp.valueOf(sdf1.format(currentTime));
    return time;
  }
  
  public static Timestamp getTodayEndTimeStamp()
  {
    Timestamp currentTime = getTimestamp();
    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    Timestamp time = Timestamp.valueOf(sdf1.format(currentTime));
    return time;
  }
  
  public static Timestamp getTomorrowStartTimeStamp()
  {
    Timestamp currentTime = getTimestamp();
    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    Timestamp time = Timestamp.valueOf(sdf1.format(currentTime));
    time = new Timestamp(time.getTime() + 1000L);
    return time;
  }
  
  public static Timestamp getWeekStartTimeStamp()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
    cal.set(7, 2);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = sdf.format(cal.getTime());
    Timestamp ts = Timestamp.valueOf(time);
    return ts;
  }
  
  public static Timestamp getMonthStartTimeStamp()
  {
    Calendar c = Calendar.getInstance();
    c.add(2, 0);
    c.set(5, 1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    String time = sdf.format(c.getTime());
    Timestamp ts = Timestamp.valueOf(time);
    return ts;
  }
  
  public static Timestamp getMonthEndTimeStamp()
  {
    Calendar ca = Calendar.getInstance();
    ca.set(5, ca.getActualMaximum(5));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    String time = sdf.format(ca.getTime());
    Timestamp ts = Timestamp.valueOf(time);
    return ts;
  }
  
  public static int getDayOfWeekFromTimeStamp(Timestamp timestamp)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date(timestamp.getTime()));
    int dayOfWeek = c.get(7);
    switch (dayOfWeek)
    {
    case 1: 
      return 7;
    case 2: 
      return 1;
    case 3: 
      return 2;
    case 4: 
      return 3;
    case 5: 
      return 4;
    case 6: 
      return 5;
    case 7: 
      return 6;
    }
    return 0;
  }
  
  public static int getTodayWeekDayNum()
  {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date(System.currentTimeMillis()));
    int dayOfWeek = c.get(7);
    switch (dayOfWeek)
    {
    case 1: 
      return 7;
    case 2: 
      return 1;
    case 3: 
      return 2;
    case 4: 
      return 3;
    case 5: 
      return 4;
    case 6: 
      return 5;
    case 7: 
      return 6;
    }
    return 0;
  }
  
  public static Map uploadFile(String relativePath, MultipartFile file, HttpServletRequest request)
    throws BizException
  {
    Map<String, Object> result = new HashMap();
    String path = request.getSession().getServletContext().getRealPath("upload");
    path = path + relativePath;
    String uploadFileName = file.getOriginalFilename();
    if (uploadFileName.indexOf("xls") < 0) {
      throw new BizException("请提交excel表格文件");
    }
    if (Util.isEmpty(uploadFileName)) {
      throw new BizException("文件内容为空");
    }
    String[] suffix = uploadFileName.split("\\.");
    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix[(suffix.length - 1)];
    
    File targetFile = new File(path, fileName);
    targetFile.setWritable(true, false);
    if (!targetFile.exists()) {
      targetFile.mkdirs();
    }
    try
    {
      file.transferTo(targetFile);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new BizException("文件IO存储异常");
    }
    result.put("fileName", uploadFileName.split("\\.")[0]);
    result.put("realPath", path + System.getProperty("file.separator") + fileName);
    result.put("serverPath", request.getContextPath() + "/upload" + relativePath.replaceAll("\\\\", "/") + "/" + fileName);
    return result;
  }
  
  public static int cnWeekDayToNum(String weekDay)
  {
    if (weekDay.equals("星期一")) {
      return 1;
    }
    if (weekDay.equals("星期二")) {
      return 2;
    }
    if (weekDay.equals("星期三")) {
      return 3;
    }
    if (weekDay.equals("星期四")) {
      return 4;
    }
    if (weekDay.equals("星期五")) {
      return 5;
    }
    if (weekDay.equals("星期六")) {
      return 6;
    }
    if (weekDay.equals("星期七")) {
      return 7;
    }
    return 0;
  }
  
//  public static void sendSms(String mobile, String outlet, String msgContent)
//  {
//    CloudConfig config = new CloudConfig();
//    config.setUserName(AppToolInit.BASSUSERNAME);
//    config.setPassword(AppToolInit.BASSPASSWORD);
//    config.setUrl("http://baas.ruijieyun.com/");
//    JSONObject param = new JSONObject();
//    param.put("phoneNumber", mobile);
//    param.put("msgContent", msgContent);
//    param.put("outlet", outlet);
//    param.put("outletType", "");
//    param.put("outletAddress", "");
//    try
//    {
//      SmsService.sendSms(config, param);
//    }
//    catch (BizException e)
//    {
//      logger.info(e.getErrorCode() + "================:" + e.getErrorDiscription());
//      e.printStackTrace();
//    }
//  }
  
  public static String getIpAddr(HttpServletRequest request)
  {
    String ip = request.getHeader("x-forwarded-for");
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
    {
      ip = request.getRemoteAddr();
      if (ip.equals("127.0.0.1"))
      {
        InetAddress inet = null;
        try
        {
          inet = InetAddress.getLocalHost();
        }
        catch (UnknownHostException e)
        {
          e.printStackTrace();
        }
        ip = inet.getHostAddress();
      }
    }
    if ((ip != null) && (ip.length() > 15) && 
      (ip.indexOf(",") > 0)) {
      ip = ip.substring(0, ip.indexOf(","));
    }
    return ip;
  }
  
  public static int getCheckResult(String line)
  {
    Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTl=\\d+)", 2);
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      return 1;
    }
    return 0;
  }
  
  public static boolean ping(String ipAddress)
  {
    return ping(ipAddress, 4, 4000);
  }
  
  public static boolean ping(String ipAddress, int timeOut)
  {
    return ping(ipAddress, 4, timeOut);
  }
  
  public static boolean ping(String ipAddress, int pingTimes, int timeOut)
  {
    BufferedReader in = null;
    Runtime runtime = Runtime.getRuntime();
    String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
    try
    {
      Process process = runtime.exec(pingCommand);
      if (isEmpty(process)) {
        return false;
      }
      in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
      int connectedCount = 0;
      String line = null;
      while ((line = in.readLine()) != null) {
        connectedCount += getCheckResult(line);
      }
      return connectedCount == pingTimes;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return false;
    }
    finally
    {
      try
      {
        in.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
