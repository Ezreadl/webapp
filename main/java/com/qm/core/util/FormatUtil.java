package com.qm.core.util;

import java.text.DecimalFormat;
import java.util.Map;

public class FormatUtil {
    
//  private static DecimalFormat df=new DecimalFormat("##0.00");
//  
//  private static DecimalFormat df2=new DecimalFormat("##0.###");
    
    private static ThreadLocal<DecimalFormat> threadLocal=new ThreadLocal<DecimalFormat>();
    
    private static ThreadLocal<DecimalFormat> threadLocal2=new ThreadLocal<DecimalFormat>();
    
    private static DecimalFormat getDf(){
        DecimalFormat df=threadLocal.get();
        if(df==null){
            df=new DecimalFormat("##0.00");
            threadLocal.set(df);
        }
        return df;
    }
    
    private static DecimalFormat getDf2(){
        DecimalFormat df2=threadLocal.get();
        if(df2==null){
            df2=new DecimalFormat("##0.###");
            threadLocal2.set(df2);
        }
        return df2;
    }
    
    public static Object format(Object obj){
        if(obj==null||"".equals(obj)){
            return "";
        }
        try {
            return getDf().format(obj);
        } catch (Exception e) {
            try {
                Object d=getDf().parse(obj.toString().trim());
                return getDf().format(d);
            } catch (Exception e1) {}
            return obj;
        }
    }
    
    public static Object format2(Object obj){
        if(obj==null||"".equals(obj)){
            return "";
        }
        try {
            return getDf2().format(obj);
        } catch (Exception e) {
            try {
                Object d=getDf2().parse(obj.toString().trim());
                return getDf2().format(d);
            } catch (Exception e1) {}
            return obj;
        }
    }
    
    //流量单位-1无,  0bit/s,1byte/s,2Kb/s,3KB/s,4Mb/s,5MB/s,6Gb/s,7GB/s,8Tb/s,9TB/s
    public static Object formatSpeedRate(Object obj,int unit){
        if(obj==null||"".equals(obj)){
            return "";
        }
        try {
            if(unit==-1){
                return format2(obj);
            }
            double d=Double.parseDouble(obj.toString());
            String[] unitArr={"bit/s","byte/s","Kb/s","KB/s","Mb/s","MB/s","Gb/s","GB/s","Tb/s","TB/s"};
            int unitIndex=unit;
            while(d>=1024){
                d=d/1024;
                unitIndex=unitIndex+2;
                if(unitIndex>9){
                	break;
                }
            }
            return format2(d)+unitArr[unitIndex];
            /*if(unit==1){
                d=d*8;
            }
            String[] unitArr={"b/s","Kb/s","Mb/s","Gb/s","Tb/s","",""};
            int unitIndex=0;
            while(d>=1024){
                d=d/1024;
                unitIndex++;
            }
            return format2(d)+unitArr[unitIndex];*/
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return obj;
    }
    
    //格式化大小-1无,  0bit,1byte,2Kb,3KB,4Mb,5MB,6Gb,7GB,8Tb,9TB
    public static Object formatSize(Object obj,int unit){
        if(obj==null||"".equals(obj)){
            return "";
        }
        try {
            if(unit==-1){
                return format2(obj);
            }
            double d=Double.parseDouble(obj.toString());
            String[] unitArr={"bit","byte","Kb","KB","Mb","MB","Gb","GB","Tb","TB"};
            int unitIndex=unit;
            while(d>=1024){
                d=d/1024;
                unitIndex=unitIndex+2;
                if(unitIndex>9){
                	break;
                }
            }
            return format2(d)+unitArr[unitIndex];
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return obj;
    }
    
    public static String getInterUnitDesc(int unit){
        if(unit<0){
            return "";
        }
        String[] unitArr={"bit/s","byte/s","Kb/s","KB/s","Mb/s","MB/s","Gb/s","GB/s","Tb/s","TB/s","","","",""};
        return unitArr[unit];
    }
    
    //type=0 原格式，type=1格式化
    public static String formatCnt(Long cnt,int type){
        if(type==2){
            type=0;
        }
        return formatCnt(cnt,null,type);
    }
    //type=0 原格式，type=1格式化，type=2,占比
    public static String formatCnt(Long cnt,Long total,int type){
        if(cnt==null){
            return "";
        }
        if(type==0){
            return cnt.toString();
        }else if(type==1){
            StringBuilder sb=new StringBuilder();
            if(cnt>10000000){
                double ret=cnt;
                ret=ret/100000000;
                return format(ret)+"亿";
            }else if(cnt>10000){
                double ret=cnt;
                ret=ret/10000;
                return format(ret)+"万";
            }else if(sb.length()<=0){
                sb.append(cnt);
            }
            return sb.toString();
        }else if(type==2){
            return formatRate(cnt,total);
        }
        return cnt.toString();
    }
    
    public static String formatRate(Long mole,Long deno){
        if(mole==null || deno==null){
            return "";
        }
        if(deno==0){
            return "-";
        }
        double ret=mole;
        ret=(ret/deno)*100;
        try {
            return getDf2().format(ret)+"%";
        } catch (Exception e) {
            return ret+"%";
        }
    }
    
    //showType==0或1格式化；==2则将dataMap中的fieldName的Long值除以dataAllMap中的fieldName的Long值，将占比结果格式化
    public static void formatResultCnt(String fieldName,Map<String,Object> dataMap,Map<String,Object> dataAllMap,int showType){
        if(showType==2){
            Long total=0L;
            Object obj=dataAllMap.get(fieldName);
            if(dataAllMap!=null && obj!=null){
                total=Long.parseLong(obj.toString());
            }
            Object obj2=dataMap.get(fieldName);
            if(obj2!=null){
            	dataMap.put(fieldName,FormatUtil.formatCnt(Long.parseLong(obj2.toString()),total,showType));
            }else{
            	dataMap.put(fieldName,"-");
            }
        }else{
        	Object obj=dataMap.get(fieldName);
        	if(obj!=null){
        		dataMap.put(fieldName,FormatUtil.formatCnt(Long.parseLong(obj.toString()),null,showType));
	        }else{
	        	dataMap.put(fieldName,"-");
	        }
        }
    }
    
    //将dataMap中的key为fieldName的Long值 除以totalCnt 值*100后格式化，转储名称为retFieldName，原有fieldName保留
    public static void formatResultRate(String fieldName,String retFieldName,Map<String,Object> dataMap,long totalCnt){
    	Object obj=dataMap.get(fieldName);
        if(null!=dataMap && null!=obj){
        	try{
        	 dataMap.put(retFieldName,FormatUtil.formatRate(Long.parseLong(obj.toString()),totalCnt));
        	}catch(Exception e){
        		dataMap.put(retFieldName,"-");
        	}
        }else{
        	dataMap.put(retFieldName,"-");
        }
           
    }
  //showType==0或1格式化；==2则将dataMap中的fieldName的Long值除以totalCnt值，将占比结果格式化
    public static void formatResultRate(String fieldName,Map<String,Object> dataMap,long total,int showType){
    	 if(showType==2){
             Object obj2=dataMap.get(fieldName);
             if(obj2!=null){
             	dataMap.put(fieldName,FormatUtil.formatCnt(Long.parseLong(obj2.toString()),total,showType));
             }else{
             	dataMap.put(fieldName,"-");
             }
         }else{
         	Object obj=dataMap.get(fieldName);
         	if(obj!=null){
         		dataMap.put(fieldName,FormatUtil.formatCnt(Long.parseLong(obj.toString()),null,showType));
 	        }else{
 	        	dataMap.put(fieldName,"-");
 	        }
         }
           
    }
    //showType==0或1格式化；==2则将dataMap中的fieldName的Long值除以totalCnt值，将占比结果格式化
    public static void formatResultRate(String fieldName,String retFieldName,Map<String,Object> dataMap,long total,int showType){
    	 if(showType==2){
             Object obj2=dataMap.get(fieldName);
             if(obj2!=null){
             	dataMap.put(retFieldName,FormatUtil.formatCnt(Long.parseLong(obj2.toString()),total,showType));
             }else{
             	dataMap.put(retFieldName,"-");
             }
         }else{
         	Object obj=dataMap.get(fieldName);
         	if(obj!=null){
         		dataMap.put(retFieldName,FormatUtil.formatCnt(Long.parseLong(obj.toString()),null,showType));
 	        }else{
 	        	dataMap.put(retFieldName,"-");
 	        }
         }
           
    }
    //将dataMap中的key为fieldName的double值*100后格式化
    public static void formatResultRate(String fieldName,Map<String,Object> dataMap){
        if(null!=dataMap){
        	Object obj=dataMap.get(fieldName);
            if(obj==null){
                dataMap.put(fieldName,"-");
            }else{
            	double cnt=Double.parseDouble(obj.toString());
                dataMap.put(fieldName,FormatUtil.format2(cnt*100)+"%");
            }
        }
    }
    
  //将dataMap1-dataMap2中的key为fieldName的double值*100后格式化
    public static void formatResultRate(String fieldName,Map<String,Object> dataMap1,Map<String,Object> dataMap2,Map<String,Object> retMap){
        if(null!=dataMap1 && null!=dataMap2){
        	Object obj1=dataMap1.get(fieldName);
        	Object obj2=dataMap2.get(fieldName);
            if(obj1==null || obj2==null || obj1.toString().equals("-") || obj2.toString().equals("-") ){
            	retMap.put(fieldName,"-");
            }else{
            	double cnt1=Double.parseDouble(obj1.toString());
            	double cnt2=Double.parseDouble(obj2.toString());
            	retMap.put(fieldName,FormatUtil.format2((cnt1-cnt2)*100)+"%");
            }
        }else{
        	retMap.put(fieldName,"-");
        }
    }
    //dataMap1 dataMap2 里面已经是百分比的情况
    public static void formatResultRate2(String fieldName,Map<String,Object> dataMap1,Map<String,Object> dataMap2,Map<String,Object> retMap){
        if(null!=dataMap1 && null!=dataMap2){
        	Object obj1=dataMap1.get(fieldName);
        	Object obj2=dataMap2.get(fieldName);
            if(obj1==null || obj2==null || obj1.toString().equals("-") || obj2.toString().equals("-") ){
            	retMap.put(fieldName,"-");
            }else{
            	double cnt1=Double.parseDouble(obj1.toString().replace("%", ""));
            	double cnt2=Double.parseDouble(obj2.toString().replace("%", ""));
            	retMap.put(fieldName,FormatUtil.format2((cnt1-cnt2))+"%");
            }
        }else{
        	retMap.put(fieldName,"-");
        }
    }
    
    
    
    public static void main(String[] args) {
       /* System.out.println(formatCnt(12312343242L,0L,0));
        System.out.println(formatCnt(12312343242L,0L,1));
        System.out.println(formatCnt(12312343242L,0L,2));*/
        
    	String str = FormatUtil.format2((0.3-0.55678)*100)+"%";
        System.out.println(str);
        
        if(str.startsWith("-")){
        	System.out.println("----");
        }
    }
}
