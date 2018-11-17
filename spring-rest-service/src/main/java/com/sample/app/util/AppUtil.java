package com.sample.app.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sample.app.constants.AppConstants;

public final class AppUtil {

	public AppUtil() {

	}

	public static String getStartDate(final Map<String, String> reqMap) {
		return (String) reqMap.get(AppConstants.START_DATE);
	}

	public static String getEndDate(final Map<String, String> reqMap) {
		return (String) reqMap.get(AppConstants.END_DATE);
	}

	public static String getId(final Map<String, Object> reqMap) {
		return (String) reqMap.get(AppConstants.ID);
	}
		
	public static String getIdCaps(final Map<String, Object> reqMap) {
			return (String) reqMap.get(AppConstants.CAPS_ID);
	}
	public static String getValue(final Map<String, Object> reqMap, final String key) {
		return (String) reqMap.get(key);
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String> getDateRange(final Map<String, Object> reqMap) {
		return ( Map<String,String>)reqMap.get(AppConstants.DATE_RANGE);
	}

	@SuppressWarnings("unchecked")
	public static  List<String> getHouseNum(Map<String, Object> reqMap) {
		return (List<String>) reqMap.get(AppConstants.HOUSE_NO);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getStations(Map<String, Object> reqMap) {
		
		return (List<String>) reqMap.get(AppConstants.STATIONS);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getChannels(Map<String, Object> reqMap) {
		return (List<String>) reqMap.get(AppConstants.CHANNELS);
	}

	public static String getFileTYpe(Map<String, Object> reqMap) {
		return (String) reqMap.get(AppConstants.FILE_TYPE);
	}
}
