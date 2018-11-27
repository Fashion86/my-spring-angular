package com.sample.app.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sample.app.service.AppService;

@Component
public class AppHandler {

	/**
	 * AppService instance.
	 */
	@Autowired
	private AppService appService;

	/**
	 * getEmployeeList AppHandler Method to get list of Employee.
	 * 
	 * @param httpServletRequest - httpServletRequest from request.
	 * @param reqMap - Request Map.
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> getEmployeeList(HttpServletRequest httpServletRequest,
			Map<String, Object> reqMap) {

		boolean success = false;
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			final Object employeeList = appService.getEmployeeList(reqMap);
			resultMap.put("response", employeeList);
			success = true;
			resultMap.put("success", success);
		} catch (final Exception e) {
			resultMap.put("errorMsg", "Exception happened.");
			resultMap.put("success", success);
			resultMap.put("response", "");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

	}

	/**
	 * getAppRecordBtwDates AppHandler method to get App Records between two date
	 * @param httpServletRequest - httpServletRequest from request.
	 * @param reqMap - Request Map.
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> getAppRecordBtwDates(HttpServletRequest httpServletRequest,
			Map<String, Object> reqMap) {
		boolean success = false;
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			final Object appDataList = appService.getAppRecordBtwDates(reqMap);
			resultMap.put("response", appDataList);
			success = true;
			resultMap.put("success", success);
		} catch (final Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "Exception happened.");
			resultMap.put("success", success);
			resultMap.put("response", "");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	/**
	 * getAppRecordBtwDates AppHandler method to get App Records between two date
	 * 
	 * @param httpServletRequest - httpServletRequest from request.
	 * @param reqMap             - Request Map.
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> getAppRowData(HttpServletRequest httpServletRequest,
			Map<String, Object> reqMap) {
		boolean success = false;
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			final Object appDataList = appService.getRowData(reqMap);
			resultMap.put("response", appDataList);
			success = true;
			resultMap.put("success", success);
		} catch (final Exception e) {
			resultMap.put("errorMsg", "Exception happened.");
			resultMap.put("success", success);
			resultMap.put("response", "");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	/**
	 * getAppRecordBtwDates AppHandler method to get App Records between two date
	 * 
	 * @param httpServletRequest - httpServletRequest from request.
	 * @param reqMap             - Request Map.
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> updateAppRowData(HttpServletRequest httpServletRequest,
                                                                ArrayList<Map<String, Object>> reqMap) {
		boolean success = false;
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			final List<String> appDataList = appService.updateRowData(reqMap);
			resultMap.put("response", appDataList);
			success = true;
			resultMap.put("success", success);
		} catch (final Exception e) {
			success = false;
			resultMap.put("errorMsg", "Exception happened.");
			resultMap.put("success", success);
			resultMap.put("response", "");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

}
