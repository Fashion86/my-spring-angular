package com.sample.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.app.dao.AppDao;
import com.sample.app.model.AppData;
import com.sample.app.model.Employee;
import com.sample.app.util.AppUtil;
import com.sample.app.util.ConnectionUtils;

@Component
public class AppService {

	@Autowired
	private AppDao appDao;

	public Object getEmployeeList(Map<String, Object> reqMap) {

		Connection conn = null;
		try {
			conn = ConnectionUtils.getDBConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Employee> empList = new ArrayList<Employee>();
		try {
			empList = appDao.getEmployeeList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}

	public Object getAppRecordBtwDates(Map<String, Object> reqMap) {

		Connection conn = null;
		Map<String, String> dateRange = AppUtil.getDateRange(reqMap);
		
		String startDate = AppUtil.getStartDate(dateRange);
		String endDate = AppUtil.getEndDate(dateRange);
		List<String> houseNum = AppUtil.getHouseNum(reqMap);
		List<String> stations = AppUtil.getStations(reqMap);
		List<String> channels = AppUtil.getChannels(reqMap);
		String fileType= AppUtil.getFileTYpe(reqMap);
		
		
		
		try {
			conn = ConnectionUtils.getDBConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<AppData> appDataList = new ArrayList<AppData>();
		try {
			
			appDataList = appDao.getAppDataList(conn, startDate, endDate, houseNum,stations,channels, fileType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appDataList;
	}


	public Object getRowData(Map<String, Object> reqMap) {

		Connection conn = null;

		String id = AppUtil.getId(reqMap);
		try {
			conn = ConnectionUtils.getDBConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AppData appData = new AppData();
		try {
			appData = appDao.getAppDataRow(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appData;
	}

	public Object updateRowData(Map<String, Object> reqMap) {

		Connection conn = null;
		Set<String> keys = reqMap.keySet();
		String id = AppUtil.getIdCaps(reqMap);
		String key = keys.stream().findFirst().get();
		String newValue = AppUtil.getValue(reqMap, key);
		try {
			conn = ConnectionUtils.getDBConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AppData appData = new AppData();
		try {
			appData = appDao.updateAppDataRow(conn, id, key, newValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appData;
	}

}
