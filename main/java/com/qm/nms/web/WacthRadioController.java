//package com.qm.nms.web;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.qm.core.util.CommonUtils;
//import com.qm.core.util.Constant;
//import com.qm.nms.domain.SnDevice;
//import com.qm.nms.domain.SnPerson;
//import com.qm.nms.domain.SnVideoDvr;
//import com.qm.nms.init.AppToolInit;
//import com.qm.nms.service.ISnDeviceService;
//import com.qm.nms.service.ISnPersonService;
//
//import exception.BizException;
//@Controller
//public class WacthRadioController {
//	@Autowired
//	private ISnDeviceService snDeviceService;
//	@Autowired
//	private ISnPersonService snPersonService;
//	@ResponseBody
//	@RequestMapping(value = "getClassScheduleDesc", method =
//			org.springframework.web.bind.annotation.RequestMethod.POST, produces =
//					"application/json; charset=UTF-8")
//	public String getClassScheduleDesc(@RequestParam(value = "id", required = true) Long id, ModelMap modelMap,
//			HttpServletRequest request) {
//		try {
//			String userIp = CommonUtils.getIpAddr(request);
//			SnPerson snPerson = snPersonService.getSnPersonByUserName("root");
//			ClassSchedule classSchedule = classScheduleService.getClassScheduleById(id);
//			Map<String, Object> playParam = new HashMap();
//			playParam.put("userName", snPerson.getPersUsername());
//			playParam.put("password", snPerson.getPersPwd());
//			if (userIp.indexOf(AppToolInit.INTRANET_SEGMENT) < 0) {
//				playParam.put("serverIp", AppToolInit.SNMS_SERVER_PUBLIC_IP);
//			} else {
//				playParam.put("serverIp", Constant.getPropertie("SERVER_IP"));
//			}
//			playParam.put("serverPort", Constant.getPropertie("SNMS_WEB_PORT"));
//			if (!CommonUtils.isEmpty(classSchedule.getLaboratory().getLabUserDevice())) {
//				SnDevice snDevice = snDeviceService
//						.getSnDeviceById(classSchedule.getLaboratory().getLabUserDevice().getDevId());
//				if (CommonUtils.isEmpty(snDevice)) {
//					playParam.put("devKey", "");
//				} else {
//					SnDevice parentDevice = snDeviceService.getParentSnDevice(snDevice);
//					SnVideoDvr snVideoDvr = snDeviceService.getSnVideoDvrByDevId(parentDevice);
//					if (userIp.indexOf(AppToolInit.INTRANET_SEGMENT) < 0) {
//						playParam.put("appUrl",
//								AppToolInit.getRemotePlaybackAppAddress(snDevice.getDevName(), parentDevice.getDevSn(),
//										parentDevice.getDevPassword(), snDevice.getDevNo(),
//										classSchedule.getStartTime(), classSchedule.getEndTime()));
//					} else {
//						playParam.put("appUrl",
//								AppToolInit.getPlaybackAppAddress(snDevice.getDevName(), parentDevice.getDevIp(),
//										snVideoDvr.getMobilePort() + "", parentDevice.getDevUsername(),
//										parentDevice.getDevPassword(), snDevice.getDevNo(),
//										classSchedule.getStartTime(), classSchedule.getEndTime()));
//					}
//					playParam.put("devKey", snDevice.getDevId());
//				}
//			} else {
//				playParam.put("devKey", "");
//			}
//			Map<String, Object> schedule = new HashMap();
//			schedule.put("status", classSchedule.getEvaluationStatus());
//			schedule.put("evaluation", classSchedule.getEvaluation());
//			schedule.put("start", CommonUtils.formatTimeStamp("yyyy-MM-dd HH:mm:ss", classSchedule.getStartTime()));
//			schedule.put("end", CommonUtils.formatTimeStamp("yyyy-MM-dd HH:mm:ss", classSchedule.getEndTime()));
//			schedule.put("moveSignal", classSchedule.getMoveSignal().getDesc());
//			schedule.put("macSignal", classSchedule.getMacSignal().getDesc());
//			if (CommonUtils.isEmpty(classSchedule.getPicPath())) {
//				schedule.put("picPath", "");
//			} else {
//				String picPath = classSchedule.getPicPath();
//				if (userIp.indexOf(AppToolInit.INTRANET_SEGMENT) < 0) {
//					schedule.put("picPath",
//							picPath.replaceAll(AppToolInit.PICTURE_SERVER, AppToolInit.PICTURE_SERVER_FOR_PUBLIC_NET));
//				} else {
//					schedule.put("picPath", picPath);
//				}
//			}
//			if (!CommonUtils.isEmpty(classSchedule.getEvaluationPerson())) {
//				if (classSchedule.getEvaluationPerson().getUserName().equals(getUser().getUserName())) {
//					schedule.put("updateStatus", Integer.valueOf(1));
//				} else {
//					schedule.put("updateStatus", Integer.valueOf(0));
//				}
//			} else {
//				schedule.put("updateStatus", Integer.valueOf(0));
//			}
//			modelMap.put("playParam", playParam);
//
//			modelMap.put("schedule", schedule);
//
//			success(modelMap);
//		} catch (BizException ex) {
//			failed(modelMap, ex);
//		}
//		return JSON.toJSONString(modelMap);
//	}
//}
