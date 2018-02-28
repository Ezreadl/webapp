package com.qm.core.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
	
	protected HashMap<String, Object> getRequestMapSingle(HttpServletRequest request) {
		HashMap<String, Object> conditions = new HashMap<String, Object>();
		Map map = request.getParameterMap();
		for (Object o : map.keySet()) {
			String key = (String) o;
			conditions.put(key, ((String[]) map.get(key))[0]);
		}
		return conditions;
	}
}
