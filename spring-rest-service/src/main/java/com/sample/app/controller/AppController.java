package com.sample.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.handler.AppHandler;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppController {

	/**
	 * AppHandler instance.
	 */
	@Autowired
	private AppHandler handler;

	/**
	 * getEmployeeList controller method to get all the employee list.
	 *
	 * @param reqMap             - Request Map
	 * @param httpServletRequest - httpServletRequest from Request
	 * @return Response Entity
	 */
	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getEmployeeList(@RequestBody final Map<String, Object> reqMap,
			final HttpServletRequest httpServletRequest) {
		return handler.getEmployeeList(httpServletRequest, reqMap);
	}

	/**
	 * getAppRecordBtwDates controller method to get the records between two date.
	 *
	 * @param reqMap             - Request Map
	 * @param httpServletRequest - httpServletRequest from Request
	 * @return Response Entity
	 */
	@RequestMapping(value = "/getAppRecordBtwDates", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getAppRecordBtwDates(@RequestBody final Map<String, Object> reqMap,
			final HttpServletRequest httpServletRequest) {
		String aaa = new String();
		return handler.getAppRecordBtwDates(httpServletRequest, reqMap);
	}

	/**
	 * getAppRecordBtwDates controller method to get the records between two date.
	 *
	 * @param reqMap             - Request Map
	 * @param httpServletRequest - httpServletRequest from Request
	 * @return Response Entity
	 */
	@RequestMapping(value = "/getAppRow", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getAppRowData(@RequestBody final Map<String, Object> reqMap,
			final HttpServletRequest httpServletRequest) {

		return handler.getAppRowData(httpServletRequest, reqMap);
	}

	/**
	 * getAppRecordBtwDates controller method to get the records between two date.
	 *
	 * @param reqMap             - Request Map
	 * @param httpServletRequest - httpServletRequest from Request
	 * @return Response Entity
	 */
	@RequestMapping(value = "/updateAppRow", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> updateAppRowData(@RequestBody final Map<String, Object> reqMap,
			final HttpServletRequest httpServletRequest) {

		return handler.updateAppRowData(httpServletRequest, reqMap);
	}
}
