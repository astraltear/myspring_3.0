package com.audien.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

public class AccessLoggerFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AccessLoggerFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		chain.doFilter(req, res);
	}

	private void sendPOST(JSONObject logString) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080//springpilot/readLog");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		logger.info("sendPOST:: " + logString.toJSONString());
		nvps.add(new BasicNameValuePair("logString", logString.toJSONString()));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		httpClient.execute(httpPost);

		httpClient.close();

	}

	public JSONObject requestParamsToJSON(HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();

		JSONObject paramJson = new JSONObject();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String v[] = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			paramJson.put(entry.getKey(), o);
		}

		jsonObj.put("params", paramJson);

		String remoteAddr = StringUtils.defaultString(request.getRemoteAddr(), "-");
		String uri = StringUtils.defaultIfEmpty(request.getRequestURI(), "");
		String url = (request.getRequestURL() == null) ? "" : request.getRequestURL().toString();
		String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
		String agent = StringUtils.defaultString(request.getHeader("User-Agent"), "-");

		String fullUrl = url;
		fullUrl += StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString;

		jsonObj.put("remoteAddr", remoteAddr);
		jsonObj.put("uri", uri);
		jsonObj.put("url", url);
		jsonObj.put("agent", agent);
		jsonObj.put("fullUrl", fullUrl);

		return jsonObj;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
