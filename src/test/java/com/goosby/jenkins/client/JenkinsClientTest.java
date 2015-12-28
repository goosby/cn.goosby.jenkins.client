package com.goosby.jenkins.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.goosby.jenkins.model.jobdetail.JenkinsJob;

public class JenkinsClientTest {
	public static JenkinsClient client;
	public static String URL = "http://192.168.138.62:8081/jenkins";
	
	
	
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
		JenkinsJob jenkinsJob = JSON.parseObject(result,JenkinsJob.class);
		assertNotNull(jenkinsJob);
	}
	
	@Test
	public void testGetApiJson(){
		String result = client.getApiJson();
		System.out.println(result);
		
	}
	
	@Test
	public void testGetBuildDetails(){
		String result = client.getBuildDetails("INFS_CAP_PE_INTERFACE", 2247l);
		System.out.println(result);
	}
	
	@Test
	public void testIsBuilding(){
		boolean result = client.isBuilding("INFS_CAP_PE_INTERFACE", 2247l);
		System.out.println(result);
	}
	
}
