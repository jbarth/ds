package com.ubs.gtp.security.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Willy Lai
 * @since 0.1
 */

public class ApiPathUtil {
	
	/**
	 * Parses the API URL and returns a list of the relevant paths.
	 * @param path
	 * @return
	 */
	public static List<String> getApiPath(String path) {
		List<String> resultList = new ArrayList<String>();
		boolean apiDetected = false;
		String[] paths = path.split("/");
		for (String p : paths) {
			if (apiDetected) {
				resultList.add(p);
			}
			if ("api".equals(p)) {
				apiDetected = true;
			}
		}
		return resultList;
	}
	
}
