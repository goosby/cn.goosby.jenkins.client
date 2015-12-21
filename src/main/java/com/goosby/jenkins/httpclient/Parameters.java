package com.goosby.jenkins.httpclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parameters {
	private final Map<String, List<String>> parameters = new TreeMap<String, List<String>>();

	public Parameters() {}

	public Parameters(Map<String, String> parameters) {
		addAll(parameters);
	}

	public void addAll(Map<String, String> parameters) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}
	}

	public Parameters add(String key, String value) {
		List<String> values = parameters.get(key);
		if (values == null) {
			values = new ArrayList<String>();
			parameters.put(key, values);
		}
		values.add(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder content = new StringBuilder();
		for (Map.Entry<String, List<String>> parameterEntry : parameters.entrySet()) {
			String key = encode(parameterEntry.getKey());
			for (String value : parameterEntry.getValue()) {
				content.append(key).append("=").append(encode(value)).append("&");
			}
		}
		if (content.length() > 0) {
			content.setLength(content.length() - 1);
		}
		return content.toString();
	}

	private String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Error("UTF-8 not supported", e);
		}
	}
}
