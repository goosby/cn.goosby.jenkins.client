package com.goosby.jenkins.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class HttpClient {
	
	private static final String TEXT_PLAIN = "text/plain";
	private static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
	private static final String APPLICATION_JSON = "application/json";
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private final int connectTimeout;
	private final int readTimeout;
	//private final Gson gson = new Gson();
	private static final int BUFFER_SIZE = 4096;

	public HttpClient(int connectTimeout, int readTimeout) {
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	public HttpResponse get(String path) {
		return get(path, TEXT_PLAIN);
	}

	public HttpResponse get(String path, String accept) {
		HttpURLConnection connection;
		try {
			connection = openConnection(path, accept);
		} catch (HttpClientException e) {
			return e.getResponse();
		}
		return readResponse(connection);
	}

	public HttpResponse postJson(String path, String value) {
		return postJson(path, value, APPLICATION_JSON);
	}

	public HttpResponse postJson(String path, String value, String accept) {
		return postContent(path, APPLICATION_JSON, value, accept);
	}

	public HttpResponse post(String path, Parameters parameters) {
		return post(path, parameters, TEXT_PLAIN);
	}

	public HttpResponse post(String path, Parameters parameters, String accept) {
		return postContent(path, FORM_URL_ENCODED, parameters.toString(), accept);
	}

	private HttpResponse postContent(String path, String contentType, String content, String accept) {
		byte[] contentBytes = content.getBytes(UTF_8);
		HttpURLConnection connection;
		try {
			connection = openConnection(path, accept);
		} catch (HttpClientException e) {
			return e.getResponse();
		}
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new Error("POST method not supported", e);
		}
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Content-Length", String.valueOf(contentBytes.length));
		try {
			try (OutputStream outputStream = connection.getOutputStream()) {
				outputStream.write(contentBytes);
			}
			return readResponse(connection);
		} catch (ConnectException | SocketTimeoutException e) {
			return new HttpResponse(e);
		} catch (IOException e) {
			return readResponse(connection);
		}
	}

	private HttpResponse readResponse(HttpURLConnection connection) {
		try {
			try (InputStream inputStream = connection.getInputStream()) {
				return new HttpResponse(200, readAll(inputStream));
			}
		} catch (ConnectException | SocketTimeoutException e) {
			return new HttpResponse(e);
		} catch (IOException e) {
			try {
				int responseCode = connection.getResponseCode();
				try (InputStream errorStream = connection.getErrorStream()) {
					return new HttpResponse(responseCode, readAll(errorStream));
				}
			} catch (IOException errorException) {
				return new HttpResponse(errorException);
			}
		}
	}

	private String readAll(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytes;
		while ((bytes = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytes);
		}
		try {
			return outputStream.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Error("UTF-8 not supported", e);
		}
	}

	private HttpURLConnection openConnection(String path, String accept) throws HttpClientException {
		URL url;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			throw new HttpClientException(new HttpResponse(e));
		}
		HttpURLConnection connection;
		try {
			connection = openConnection(url);
		} catch (MalformedURLException e) {
			throw new HttpClientException(new HttpResponse(e));
		}
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("User-Agent", "httpclient/1.0");
		connection.setRequestProperty("Accept", accept);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setConnectTimeout(connectTimeout);
		connection.setReadTimeout(readTimeout);
		return connection;
	}

	private HttpURLConnection openConnection(URL url) throws MalformedURLException, HttpClientException {
		URLConnection connection;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			throw new HttpClientException(new HttpResponse(e));
		}
		if (!(connection instanceof HttpURLConnection)) {
			throw new MalformedURLException("Expected HttpURLConnection, got " + connection.getClass().getName());
		}
		return (HttpURLConnection) connection;
	}
	
	
	
}
