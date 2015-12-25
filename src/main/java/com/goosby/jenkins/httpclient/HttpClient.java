package com.goosby.jenkins.httpclient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClient {
	
	public static CloseableHttpClient httpClient;
	
	/**
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JenkinsResponse getWithParameters(String url,Map<String,String> parameters){
		httpClient = HttpClientBuilder.create().build();
		HttpGet method = null;
		int code = 0;
		String jenkinsResponse = null;
		String requestParameter = buildParameters(parameters);
		if(null != requestParameter && !requestParameter.equals("")){
			if(url.endsWith("?")){
				method = new HttpGet(url + requestParameter);
			}else{
				method = new HttpGet(url + "?" + requestParameter);
			}
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(method);
				if(response != null){
					code = response.getStatusLine().getStatusCode();
					jenkinsResponse = response.getEntity().toString();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				method.abort();
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new JenkinsResponse(code,jenkinsResponse);
	}
	
	/**
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static int postWithParameters(String url,Map<String,String> parameters){
		httpClient = HttpClientBuilder.create().build();
		int code = 0;
		
		return code;
	}
	
	public static JenkinsResponse postWithOutParameters(String url){
		httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpPost method = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(method);
			if(response != null){
				code = response.getStatusLine().getStatusCode();
				jenkinsResponse = response.getEntity().toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JenkinsResponse(code,jenkinsResponse);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static JenkinsResponse getWithOutParameter(String url){
		httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpGet method = new HttpGet(url);
		CloseableHttpResponse  response = null;
		try {
			response = httpClient.execute(method);
			if(response != null){
				code = response.getStatusLine().getStatusCode();
				jenkinsResponse = response.getEntity().toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			method.abort();
			try {
				response.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JenkinsResponse(code,jenkinsResponse);
	}
	
	/**
	 * 
	 * @param url
	 * @param xmlBody
	 * @return
	 */
	public static JenkinsResponse postBodyWithXML(String url,String xmlBody){
		httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpPost method = new HttpPost(url);
		StringEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			if(null != xmlBody && !"".equals(xmlBody)){
				entity = new StringEntity(xmlBody,ContentType.create("text/xml", "utf-8"));
				method.setEntity(entity);
			}
			response = httpClient.execute(method);
			if(response != null){
				code = response.getStatusLine().getStatusCode();
				jenkinsResponse = response.getEntity().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			method.abort();
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JenkinsResponse(code,jenkinsResponse);
	}
	
	public static String buildParameters(Map<String,String> parameters){
		StringBuilder builder = new StringBuilder();
		if(parameters != null && parameters.size() > 0){
			for(Iterator<Entry<String, String>>  iterator = parameters.entrySet().iterator(); iterator.hasNext();){
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next(); 
				String key =  entry.getKey();
				String value = entry.getValue();
				builder.append(key + "=" + value + "&");
			}
		}
		String result = builder.toString();
		if(null != result && !"".equals(result)){
			result = result.substring(0, result.length()-1);
			return result;
		}
		return null;
	}
}
