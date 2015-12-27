package com.goosby.jenkins.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.goosby.jenkins.model.job.JenkinsJob;

public class JenkinsClientTest {
	public static JenkinsClient client;
	public static String URL = "http://localhost:8080";
	
	
	
	@BeforeClass
	public static void setUp(){
		client = new JenkinsClient(URL);
	}
	
	
	@Test
	public void testBuidlJob(){
		boolean result = client.buidlJob("test-git");
		assertTrue(result);
	}
	
	@Test
	public void testGetJobDetailJSON(){
		String result = client.getJobDetailJSON("test-git");
		System.out.println(result);
		JenkinsJob jenkinsJob = JSON.parseObject(result,JenkinsJob.class);
		assertNotNull(jenkinsJob);
	}
	
	@Test
	public void testGetApiJson(){
		String result = client.getApiJson();
		System.out.println(result);
		
	}
	
	
}
