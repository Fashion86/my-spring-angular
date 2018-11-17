package com.sample.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Init Controller class to check setup.
 */
@RestController
public class InitController {
	/**
	 * Init method.
	 * @param paramMap - Param map in request body
	 * @param httpServletRequest - HttpServletRequest
	 * @param headers - HttpHeaders
	 * @return String
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public final String getInitData(@RequestBody final Map<String, Object> paramMap,
			final HttpServletRequest httpServletRequest, @RequestHeader final HttpHeaders headers) {
		
		return "INIT";
		
	}
}
