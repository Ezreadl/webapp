package com.qm.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
    
	public static Date getDate(){
		return new Date();
	}
	public static String getYm(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
		return df.format(new Date());
	}
	public static String getYmd(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	public static String getHms(){
		SimpleDateFormat df=new SimpleDateFormat(" HH:mm:ss");
		return df.format(new Date());
	}
	public static String getHms2(){
		SimpleDateFormat df=new SimpleDateFormat("HHmmss");
		return df.format(new Date());
	}
	public static String getYmd(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static String getYmdHms(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	public static String getYmdHms(long time){
		Date date=new Date(time);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	public static Date parseYmdHms(String date){
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
	public static  String parseMD(Object date){
		try{
			SimpleDateFormat df=new SimpleDateFormat("MM-dd");
			return df.format(date);
		}catch (Exception e){
			return (String) date;
		}
	}
	public static Date parseYmd(String date){
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
	public static String getYmdHms(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	public static String getYYYYMMDD(){
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	public static String getYYYYMMDD(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	public static String getYYYYMMDD(int addDay){
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, addDay);
		return df.format(c.getTime());
	}
	public static int getYearByDate(Date date){
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR);
	}
	public static Date getyyyyMMddHHmmssDate(String source){
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
			return dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	public static String getyyyyMMddHHmmssDate(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}
	public static String getyyyyMMddHHmmssDate(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date());
	}
	public static String getyyMMddHHmm(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmm");
		return dateFormat.format(new Date());
	}
	public static String getDaysAgoYMD(int day){
		return getDaysAgoYMD(new Date(),day);
	}
	public static String getDaysAgoYMD(Date date,int day){
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day);
		return df.format(c.getTime());
	}
	public static String getDaysAgo(int day){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -day+1);
		return df.format(c.getTime());
	}
	public static String getDaysAgo(Date date,int day){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -day+1);
		return df.format(c.getTime());
	}
	
	public static String getMonthAgo(String dateStr,int month) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=df.parse(dateStr);
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -month);
		return df.format(c.getTime());
	}
	public static String getWeekAgo(String dateStr,int week) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=df.parse(dateStr);
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -week);
		return df.format(c.getTime());
	}
	public static Date getWeeksAgo(Date date,int week) throws Exception{
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_MONTH, -week);
//		return df.format(c.getTime());
		return c.getTime();
	}		
	public static String getDayAgo(String dateStr,int day) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=df.parse(dateStr);
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -day);
		return df.format(c.getTime());
	}
	public static String getYearStart(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);
		//c.add(Calendar.MONTH, -month);
		return df.format(c.getTime())+" 00:00:00";
	}
	public static String format(Object date){
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
			return dateFormat.format(date);
		} catch (Exception e) {
			return ""+date;
		}
	}
	public static String format_ymdhms(Object date){
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.format(date);
		} catch (Exception e) {
			return ""+date;
		}
	}
	//获取时差
	public static String getTimeDiff(String startTime,String endTime){
		try {
			long stime=DateUtil.parseYmdHms(startTime).getTime();
			long etime=DateUtil.parseYmdHms(endTime).getTime();
			long t=etime-stime;
			long hour=t/(1000*60*60);
			long minute=(t/(1000*60)-hour*60);
			long second=(t/1000)%60;
			String h=hour<10?"0"+hour:hour+"";
			String m=minute<10?"0"+minute:minute+"";
			String s=second<10?"0"+second:second+"";
			return h+":"+m+":"+s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//获取时差
	public static String getTimeDiff(long time){
		long hour=time/(1000*60*60);
		long minute=(time/(1000*60)-hour*60);
		long second=(time/1000)%60;
		String h=hour<10?"0"+hour:hour+"";
		String m=minute<10?"0"+minute:minute+"";
		String s=second<10?"0"+second:second+"";
		return h+":"+m+":"+s;
	}
	
	//获取当月的开始时间和下月开始时间
	public static String[] getMonthSect(){
		return getMonthSect(new Date());
	}
	public static String[] getMonthSect(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
		String startTime=df.format(calendar.getTime())+"-01 00:00:00";
		calendar.add(Calendar.MONTH,1);
		String endTime=df.format(calendar.getTime())+"-01 00:00:00";
		return new String[]{startTime,endTime};
	}
	//根据年月获取该年月的第一天和最后一天的时间
	public static String[] getYearMonthRangeDateTime(int year,int month){
		String monthStr=month<10?"0"+month:month+"";
		String startTime=year+"-"+monthStr+"-01 00:00:00";
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(DateUtil.parseYmdHms(startTime));
		calendar.add(Calendar.MONTH,1);
		calendar.set(Calendar.DAY_OF_MONTH,-1);
		int endDay=calendar.get(Calendar.DAY_OF_MONTH);
		String endDayStr=endDay<10?"0"+endDay:""+endDay;
		String endTime=year+"-"+monthStr+"-"+endDayStr+" 23:59:59";
		return new String[]{startTime,endTime};
	}
	//根据年月获取该年月的第一天和最后一天的日期
	public static String[] getYearMonthRangeDate(int year,int month){
		String monthStr=month<10?"0"+month:month+"";
		String startDate=year+"-"+monthStr+"-01";
		String startTime=year+"-"+monthStr+"-01 00:00:00";
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(DateUtil.parseYmdHms(startTime));
		calendar.add(Calendar.MONTH,1);
		calendar.set(Calendar.DAY_OF_MONTH,-1);
		int endDay=calendar.get(Calendar.DAY_OF_MONTH);
		String endDayStr=endDay<10?"0"+endDay:""+endDay;
		String endDate=year+"-"+monthStr+"-"+endDayStr;
		return new String[]{startDate,endDate};
	}
	//根据年月获取该年月一个月的时间间距
	public static String[] getDiffOneMonthRangeDateTime(int year,int month) throws Exception{
		String monthStr=month<10?"0"+month:month+"";
		Calendar calendar=Calendar.getInstance();
		int curMonth=calendar.get(Calendar.MONTH);
		if(curMonth+1==month){
			int curDay=calendar.get(Calendar.DAY_OF_MONTH);
			String curDayStr=curDay<10?"0"+curDay:curDay+"";
			String endTime=year+"-"+monthStr+"-"+curDayStr+" 23:59:59";
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate=df.parse(endTime);
			calendar.setTime(endDate);
			calendar.add(Calendar.MONTH,-1);
			int startDay=calendar.get(Calendar.DAY_OF_MONTH);
			String startDayStr=startDay<10?"0"+startDay:""+startDay;
			String startTime=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+startDayStr+" 00:00:00";
			return new String[]{startTime,endTime};
		}else{
			return getYearMonthRangeDateTime(year, month);
		}
	}
	
	public static String getYmd(String time,String defaultValue){
		if(StringUtil.isBlank(time)){
			return defaultValue;
		}else{
			String date=time.replaceAll("年|月|/","-").replaceAll("日","").replaceAll("T|t"," ").substring(0,10);
			return date;
		}
	}
	
	public static String getYmdHmsStrStart(String startTime,String defaultValue){
		if(StringUtil.isBlank(startTime)){
			return defaultValue;
		}else{
			startTime=startTime.replaceAll("年|月","-").replaceAll("日","").replaceAll("T|t"," ");
			if(startTime.length()<=10){
				startTime=startTime+" 00:00:00";
			}
			return startTime;
		}
	}
	
	public static String getYmdHmsStrEnd(String endTime,String defaultValue){
		if(StringUtil.isBlank(endTime)){
			return defaultValue;
		}else{
			endTime=endTime.replaceAll("年|月","-").replaceAll("日","").replaceAll("T|t"," ");
			if(endTime.length()<=10){
				endTime=endTime+" 23:59:59";
			}
			return endTime;
		}
	}
	
	public static Date getYmdHmsDateStart(String startTime,Date defaultValue){
		startTime=getYmdHmsStrStart(startTime,null);
		if(StringUtil.isBlank(startTime)){
			return defaultValue;
		}else{
			return DateUtil.parseYmdHms(startTime);
		}
	}
	
	public static Date getYmdHmsDateEnd(String endTime,Date defaultValue){
		endTime=getYmdHmsStrEnd(endTime,null);
		if(StringUtil.isBlank(endTime)){
			return defaultValue;
		}else{
			return DateUtil.parseYmdHms(endTime);
		}
	}
	
	 /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }
    
    public static String getFirstDayOfWeekToString(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(parseYmd(date));
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }
    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }
    public static String getLastDayOfWeekToString(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(parseYmd(date));
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); // Saturday
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }
    public static String getFirstDayOfThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }
    public static String getLastDayOfThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 4); // Saturday
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }
    
    
    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }
    public static String getFirstDayOfMonthToString(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseYmd(date));
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    public static String getLastDayOfMonthToString(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseYmd(date));
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                                    getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                                   getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                                       getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    /** 
    * 获得指定日期的前一天 
    * @param curDay 
    * @return 
    * @throws Exception 
    */ 
    public static String getYesterdayStr (String curDay){ 
	    Calendar c = Calendar.getInstance(); 
	    Date date=null; 
	    try { 
	    	date = new SimpleDateFormat("yyyy-MM-dd").parse(curDay); 
	    } catch (ParseException e) { 
	    	e.printStackTrace(); 
	    } 
	    c.setTime(date); 
	    int day=c.get(Calendar.DATE); 
	    c.set(Calendar.DATE,day-1); 
	
	    String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	    return dayBefore; 
    } 
    /** 
     * 获得前一天 
     * @param curDay 
     * @return 
     * @throws Exception 
     */ 
     public static String getYesterdayStr(){ 
 	    Calendar c = Calendar.getInstance(); 
 	    Date date=new Date(); 
 	    c.setTime(date); 
 	    int day=c.get(Calendar.DATE); 
 	    c.set(Calendar.DATE,day-1); 
 	
 	    String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
 	    return dayBefore; 
     } 
    
    /** 
     * 获得指定日期的前几天
     * @param curDay 
     * @return 
     * @throws Exception 
     */ 
     public static String getDiffDayStr (String curDay,int difDay){ 
 	    Calendar c = Calendar.getInstance(); 
 	    Date date=null; 
 	    try { 
 	    	date = new SimpleDateFormat("yyyy-MM-dd").parse(curDay); 
 	    } catch (ParseException e) { 
 	    	e.printStackTrace(); 
 	    } 
 	    c.setTime(date); 
 	    int day=c.get(Calendar.DATE); 
 	    c.set(Calendar.DATE,day-difDay); 
 	
 	    String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
 	    return dayBefore; 
     } 
    
     /** 
      * Wed May 18 18:42:00 CST 2016 解析这种格式输出字符串
      * @return 
      * @throws Exception 
      */ 
      public static String getParseDateDayStr (String dayStr){ 
          SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
          String sDate = "";
          try
          {
          	  Date date=sdf1.parse(dayStr);
              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
              sDate = sdf.format(date);
          }
          catch (ParseException e)
          {
              e.printStackTrace();
          }
          return sDate;
      } 
      /** 
       * 2016-09-06T07:10:53.248Z 解析这种格式输出字符串
       * @return 
       * @throws Exception 
       */ 
       public static Date getParseTZDate(String date){ 
    	  // String date = "2016-09-06T07:10:53.248Z"; 
	       	date = date.replace("Z", " UTC");//注意是空格+UTC
	       	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
	       	Date d = null;
			try {
				d = format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
           return d;
       } 
       
   //验证参数格式是否满足指定格式，如果为null返回默认值，allowPrex：是否允许只满足format格式前缀，异常返回"",trim：是否允许param长度大于format
   public static String validate(String param,String format,String defalut,int[] allowLen,boolean trim){
	   if(param==null || "".equals(param.trim())){
		   return defalut;
	   }
	   if(allowLen==null){
		   allowLen=new int[]{format.length()};
	   }
	   if(param.length()>format.length()){
		   if(trim){
			   param=param.substring(0,format.length());
		   }else{
			   return "";//格式错误，param长度大于format
		   }
	   }
	   boolean matchLen=false;
	   for(int a:allowLen){
		   if(param.length()==a){
			   matchLen=true;
			   break;
		   }
	   }
	   if(!matchLen){
		   return "";//不匹配长度
	   }
	   try {
		   if(param.length()<format.length()){
			   format=format.substring(0,param.length());
		   }
		   SimpleDateFormat df=new SimpleDateFormat(format);
		   df.parse(param);
		   return param;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "";
   }
   public static String getTodayWeekDayNum(String rtime)
   {
     Calendar c = Calendar.getInstance();
     c.setTime(parseYmd(rtime));
     int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
     switch (dayOfWeek)
     {
     case 1: 
       return "日";
     case 2: 
       return "一";
     case 3: 
       return "二";
     case 4: 
       return "三";
     case 5: 
       return "四";
     case 6: 
       return "五";
     case 7: 
       return "六";
     }
     return "";
   }
     
     
    public static void main(String[] args)throws Exception {
    	getTodayWeekDayNum("20180108");
		/*String startTime = "2016-04-03";
		String lastWeekStartDays[] = new String[8];
		String lastWeekEndDays[] = new String[8];
		for(int i=0;i<7;i++){
			lastWeekStartDays[i] = DateUtil.getDiffDayStr(startTime, i);
			lastWeekEndDays[i] = DateUtil.getDiffDayStr(startTime, i);
		}
		lastWeekStartDays[7]=DateUtil.getDiffDayStr(startTime, 6);
		lastWeekEndDays[7] = DateUtil.getDiffDayStr(startTime, 0);
		
		System.out.println(lastWeekStartDays);
		System.out.println(lastWeekEndDays);*/
    	
//    	String date = "2016-09-06T07:10:53.248Z"; 
//    	System.out.println(getYmdHms(getParseTZDate(date)));
//    	System.out.println(validate("201701102302","yyyyMMddHH","20170112",new int[]{6,8,10},true));
//    	System.out.println(validate("20170110","yyyyMMddHH","20170112",new int[]{6,8},true));
//    	System.out.println(validate("2017011","yyyyMMddHH","20170112",new int[]{6,8},true));
//    	System.out.println(validate("20170114","yyyyMMddHH","20170112",new int[]{6,8},false));
//    	System.out.println(validate("201701102302","yyyyMMddHH","20170112",false,true));
    	
//		 System.out.println(new SimpleDateFormat("yyyyMMddHH").parse("201701102302"));
//		 System.out.println(new SimpleDateFormat("yyyyMMddHH").parse("201701112112"));
//		 System.out.println(new SimpleDateFormat("yyyyMMdd").parse("2017011023"));
//		 System.out.println(new SimpleDateFormat("yyyyMMddHH").parse("3017011023"));
	}

}




