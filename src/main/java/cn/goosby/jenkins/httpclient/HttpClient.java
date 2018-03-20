package cn.goosby.jenkins.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {

	private static final String CHARSET_UTF8="UTF-8";
	private static final int TIME_OUT = 2000;
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setExpectContinueEnabled(true)
			.setSocketTimeout(TIME_OUT)
			.setConnectTimeout(TIME_OUT)
			.setConnectionRequestTimeout(TIME_OUT)
			.build();

	/**
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JenkinsResponse getWithParameters(String url,Map<String,String> parameters){
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
			method.setConfig(requestConfig);
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(method);
				if(response != null){
					code = response.getStatusLine().getStatusCode();
					jenkinsResponse = EntityUtils.toString(response.getEntity(),CHARSET_UTF8);//.toString();
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
	public static JenkinsResponse postWithParameters(String url,Map<String,String> parameters){
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Iterator<Entry<String, String>> iterator = parameters.entrySet().iterator();iterator.hasNext();){
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			BasicNameValuePair param = new BasicNameValuePair(key, value);
			list.add(param);
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(list,CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpPost method = new HttpPost(url);
		method.setConfig(requestConfig);
		method.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(method);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(response != null){
			code = response.getStatusLine().getStatusCode();
			try {
				jenkinsResponse = EntityUtils.toString(response.getEntity());
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JenkinsResponse(code,jenkinsResponse);
	}

	/**
	 *
	 * @param url
	 * @return
	 */
	public static JenkinsResponse postWithOutParameters(String url){
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpPost method = new HttpPost(url);
		method.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(method);
			if(response != null){
				code = response.getStatusLine().getStatusCode();
				jenkinsResponse = EntityUtils.toString(response.getEntity(),CHARSET_UTF8);
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
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpGet method = new HttpGet(url);
		method.setConfig(requestConfig);
		CloseableHttpResponse  response = null;
		try {
			response = httpClient.execute(method);
			if(response != null){
				code = response.getStatusLine().getStatusCode();
				jenkinsResponse = EntityUtils.toString(response.getEntity(),CHARSET_UTF8);//.toString();
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
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		int code = 0;
		String jenkinsResponse = null;
		HttpPost method = new HttpPost(url);
		method.setConfig(requestConfig);
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
				jenkinsResponse = EntityUtils.toString(response.getEntity(),CHARSET_UTF8);;
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

	private static String buildParameters(Map<String,String> parameters){
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
