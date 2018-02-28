package com.qm.core.util;
/**
 * 表格工具 主要用于用字符串的方式 建立常用的合并的表格
 * @author Administrator
 *	zhanglei  2014-11-21
 */
public class TableUtil {
	
		/**
		 * 合并 BaseJobRecordStatistic 与  BaseJobRecordToDay
		 * @param otherCondition 其他补充查询条件  and ...
		 * @return
		 */
		public static String mergeBaseStaticAndBaseToday(String startTime,String endTime,String otherCondition){
			String minStaticDay = startTime;
			String maxStaticDay = endTime;
			if(startTime.indexOf(":")>0){
				minStaticDay = startTime.substring(0,10);
				maxStaticDay = endTime.substring(0,10);
			}else{
				startTime =  startTime + " 00:00:00";
				endTime = endTime + " 23:59:59";
			}
			StringBuilder view=new StringBuilder();
			view.append("(select equipId,itemId,");
			view.append("recordCount,alertCount,alertSwitchCount,maxAlertLevel,minAlertLevel,");
			view.append("maxResult,minResult,avgResult,");
			view.append("staticDay,maxGatherTime,minGatherTime ");
			view.append("from BaseJobRecordStatistic s ");
			view.append("where s.staticDay>='"+minStaticDay+"' and s.staticDay<='"+maxStaticDay+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append(" union all ");
			view.append("select br.equipId,br.itemId,");
			view.append("count(0) recordCount,sum(if(br.alertLevel>0,1,0)) alertCount,sum(if(br.alertSwitch>0,1,0)) alertSwitchCount,max(br.alertLevel) maxAlertLevel,min(br.alertLevel) minAlertLevel, ");
			view.append("max(br.checkResult) maxResult,min(br.checkResult) minResult,avg(br.checkResult) avgResult, ");
			view.append("max(DATE_FORMAT(br.startTime,'%Y-%m-%d')) staticDay,max(br.startTime) maxGatherTime,min(br.startTime) minGatherTime ");
			view.append("from  BaseJobRecordToDay br where state=0 and startTime>='"+startTime+"' and startTime<='"+endTime+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append(" group by br.equipId,br.itemId)  ");
			return view.toString();
		}
		
		/**
		 * 合并 equipinterfacerecordstatistic 与  equipinterfacerecordtoday
		 * @param otherCondition 其他补充查询条件  and ...
		 * @return
		 */
		public static String mergeInterStaticAndInterToday(String startTime,String endTime,String otherCondition){
			String minStaticDay = startTime;
			String maxStaticDay = endTime;
			if(startTime.indexOf(":")>0){
				minStaticDay = startTime.substring(0,10);
				maxStaticDay = endTime.substring(0,10);
			}else{
				startTime =  startTime + " 00:00:00";
				endTime = endTime + " 23:59:59";
			}
			StringBuilder view=new StringBuilder();
			view.append("(select equipId,interfaceType,interGroupId,equipInterId,");
			view.append("maxInputRate,minInputRate,avgInputRate,maxOutputRate,minOutputRate,avgOutputRate,");
			view.append("maxInputFlow,minInputFlow,avgInputFlow,maxOutputFlow,minOutputFlow,avgOutputFlow,maxInterfaceState,minInterfaceState,");
			view.append("recordCount,inAlertCount,outAlertCount,stateAlertCount,alertSwitchCount,");
			view.append("maxInputLevel,minInputLevel,maxOutputLevel,minOutputLevel,maxStateLevel,minStateLevel,");
			view.append("staticDay,maxGatherTime,minGatherTime ");
			view.append("from EquipInterfaceRecordStatistic s ");
			view.append("where s.staticDay>='"+minStaticDay+"' and s.staticDay<='"+maxStaticDay+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append(" union all ");
			view.append("select ir.equipId,ir.interfaceType,ir.interGroupId,ir.equipInterId,");
			view.append("max(ir.inputRate) maxInputRate,min(ir.inputRate) minInputRate,avg(ir.inputRate) avgInputRate,");
			view.append("max(ir.outputRate) maxOutputRate,min(ir.outputRate) minoutputrate,avg(ir.outputRate) avgOutputRate,");
			view.append("max(ir.inputFlow) maxInputFlow,min(ir.inputFlow) minInputFlow,avg(ir.inputFlow) avgInputFlow,");
			view.append("max(ir.outputFlow) maxOutputFlow,min(ir.outputFlow) minOutputFlow,avg(ir.outputFlow) avgOutputFlow,");
			view.append("count(0) recordCount,sum(if(ir.inputLevel>0,1,0)) inAlertCount,sum(if(ir.outputLevel>0,1,0)) outAlertCount,sum(if(ir.stateLevel>0,1,0)) stateAlertCount,sum(if(ir.alertSwitch>0,1,0)) alertSwitchCount,");
			view.append("max(ir.inputLevel) maxInputLevel,min(ir.inputLevel) minInputLevel,max(ir.outputLevel) maxOutputLevel,min(ir.outputLevel) minOutputLevel,");
			view.append("max(ir.stateLevel) maxStateLevel,min(ir.stateLevel) minStateLevel,max(ir.interfaceState) maxInterfaceState,min(ir.interfaceState) minInterfaceState,");
			view.append("max(DATE_FORMAT(ir.startTime,'%Y-%m-%d')) staticDay,max(ir.startTime) maxGatherTime,min(ir.startTime) minGatherTime ");
			view.append("from EquipInterfaceRecordToDay ir ");
			view.append("where ir.startTime>='"+startTime+"' and ir.startTime<='"+endTime+"' and ir.state=0 ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append("group by ir.equipId,ir.interGroupId,ir.equipInterId,ir.interfaceType)  ");
			return view.toString();
		}
		
		/**
		 * 合并 pingrecordstatistic 与  pingrecordtoday
		 * @param otherCondition 其他补充查询条件  and ...
		 * @return
		 */
		public static String mergePingStaticAndPingToday(String startTime,String endTime,String otherCondition){
			String minStaticDay = startTime;
			String maxStaticDay = endTime;
			if(startTime.indexOf(":")>0){
				minStaticDay = startTime.substring(0,10);
				maxStaticDay = endTime.substring(0,10);
			}else{
				startTime =  startTime + " 00:00:00";
				endTime = endTime + " 23:59:59";
			}
			StringBuilder view=new StringBuilder();
			view.append("(select equipId,targetId,");
			view.append("maxLostPack,minLostPack,avgLostPack,maxDelay,minDelay,avgDelay,");
			view.append("recordCount,lostAlertCount,delayAlertCount,alertSwitchCount,");
			view.append("maxLostAlertLevel,minLostAlertLevel,maxDelayAlertLevel,minDelayAlertLevel,");
			view.append("staticDay,maxGatherTime,minGatherTime  ");
			view.append("from PingRecordStatistic s ");
			view.append("where s.staticDay>='"+minStaticDay+"' and s.staticDay<='"+maxStaticDay+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append(" union all ");
			view.append("select p.equipId,p.targetId,");
			view.append("max(p.lostPack) maxLostPack,min(p.lostPack) minLostPack,avg(p.lostPack) avgLostPack,");
			view.append("max(p.delay) maxDelay,min(p.delay) minDelay,avg(p.delay) avgDelay,");
			view.append("count(0) recordCount,sum(if(p.lostAlertLevel>0,1,0)) lostAlertCount,sum(if(p.delayAlertLevel>0,1,0)) delayAlertCount,");
			view.append("sum(if(p.alertSwitch>0,1,0)) alertSwitchCount,max(p.lostAlertLevel) maxLostAlertLevel,min(p.lostAlertLevel) minLostAlertLevel,max(p.delayAlertLevel) maxDelayAlertLevel,min(p.delayAlertLevel) minDelayAlertLevel,");
			view.append("max(DATE_FORMAT(p.startTime,'%Y-%m-%d')) staticDay,max(p.startTime) maxGatherTime,min(p.startTime) minGatherTime ");
			view.append("from PingRecordToDay p ");
			view.append("where p.startTime>='"+startTime+"' and p.startTime<='"+endTime+"' and p.state=0 ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			view.append("group by p.equipId,p.targetId)  ");
			return view.toString();
		}
		
		/**
		 * 合并 equipparametercheckrecord 与  equipparametercheckrecordtmp
		 * @return
		 */
		public static String mergeParameterCheckAndTmp(String startTime,String endTime,String otherCondition){
			StringBuilder view=new StringBuilder();
			view.append("(SELECT oid, equipId, areaId, areaName, optCommand, optCommandCh, paramName, paramValue, normalResult, normalFalg, jobTaskId, staticDate, startTime, endTime, checkTime, saveFile, userNick, reason, itemId ");
			view.append("FROM equipparametercheckrecord  ");
			view.append("WHERE startTime >='"+startTime+"' AND startTime <='"+endTime+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}
			/*view.append(" union all ");
			view.append("SELECT oid, equipId, areaId, areaName, optCommand, optCommandCh, paramName, paramValue, normalResult, normalFalg, jobTaskId, staticDate, startTime, endTime, checkTime, saveFile, userNick, reason, itemId ");
			view.append("FROM equipparametercheckrecordtmp  ");
			view.append("WHERE startTime >='"+startTime+"'  AND startTime <='"+endTime+"' ");
			if(StringUtil.isNotBlank(otherCondition)){
				view.append(otherCondition);
			}*/
			view.append(")  ");
			return view.toString();
		}

		/**
		 * 合并接口表和接口组表
		 * @return
		 */
		public static String mergeEquipInterfaceAndInterGroup(){
			StringBuilder view=new StringBuilder();
			view.append(" (");
			view.append("select f.oid equipInterId,f.interfaceName,f.interGroupId,g.groupName,f.equipId from ( ");
			view.append("select 0 oid,f.interfaceName,f.interGroupId,f.equipId from equipinterface f where f.interfaceType=2 and f.delFlg=0 and f.stateFlg=0  GROUP BY f.interGroupId ");
			view.append("union all ");
			view.append("select f.oid,f.interfaceName,f.interGroupId,f.equipId from equipinterface f where f.interfaceType=0 and f.delFlg=0 and f.stateFlg=0 ");
			view.append(") f LEFT JOIN equipintergroup g on f.interGroupId=g.oid and g.stateFlg=0 and g.delFlg=0 ");
			view.append(")  ");
			return view.toString();
		} 
		
		/**
		 * 获取用户资源表
		 * @return
		 */
		public static String findUserEquipRes(){
			String sql = "select distinct d.equipId from resourcegroupdetail d,resourcegroup r,resourcegroupequiprelation g " +
					"where g.groupId = r.oid and r.oid = d.groupId and r.delFlg=0 and g.userId=? ";
			return sql;
		} 
		
		public static void main(String[] args) {
			String startTime = "2014-12-16 00:00:00";
			String endTime = "2014-12-16 23:59:59";
			
			System.out.println("/*-------------------日常合并sql-----------------------------*/");
			System.out.println(TableUtil.mergeBaseStaticAndBaseToday(startTime,endTime,null));
			System.out.println("/*-------------------接口合并sql-----------------------------*/");
			System.out.println(TableUtil.mergeInterStaticAndInterToday(startTime,endTime,null));
			System.out.println("/*-------------------链路合并sql-----------------------------*/");
			System.out.println(TableUtil.mergePingStaticAndPingToday(startTime,endTime,null));
			System.out.println("/*-------------------参数表合并sql-----------------------------*/");
			System.out.println(TableUtil.mergeParameterCheckAndTmp(startTime,endTime,null));
			System.out.println("/*-------------------接口表合并sql-----------------------------*/");
			System.out.println(TableUtil.mergeEquipInterfaceAndInterGroup());
		}
	
	
}
