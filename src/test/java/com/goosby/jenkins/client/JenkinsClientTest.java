package com.goosby.jenkins.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JenkinsClientTest {
	public static JenkinsClient client;
	public static String URL = "http://192.168.138.62:8081/jenkins";
	
	
	@BeforeClass
	public static void setUp(){
		client = new JenkinsClient(URL);
	}
	
	
	@Test
	public void testTriggerJob(){
		boolean result = client.triggerJob("test-git");
		assertTrue(result);
	}
	
	@Test
	public void testGetJobDetailJSON(){
		String result = client.getJobJSON("test-git");
		assertNotNull(result);
	}
	
	@Test
	public void testGetApiJson(){
		String result = client.getJenkinsApiJson();
		System.out.println(result);
		
	}
	
	@Test
	public void testGetBuildDetails(){
		String result = client.getBuildDetails("INFS_CAP_PE_INTERFACE", 2247l);
		System.out.println(result);
	}
	
	@Test
	public void testIsBuilding(){
		boolean result = client.isBuilding("MA_CPS_NJ_MRS", 1777l);
		assertTrue(result);
	}
	@Test
	public void testGetQueueJobItems(){
		String result = client.getQueueJobs();
		assertNotNull(result);
		JSONObject jsonObject = JSON.parseObject(result);
		JSONArray array = jsonObject.getJSONArray("items");
		System.out.println(array.size());
	}
	
	@Test
	public void testCancelQueueJob(){
		String result = client.getQueueJobs();
		JSONObject jsonObject = JSON.parseObject(result);
		JSONArray array = jsonObject.getJSONArray("items");
		assertNotNull(array);
		for(int i=0;i<array.size();i++){
			String actions = array.get(i).toString();
			JSONObject action = JSON.parseObject(actions);
			long id = action.getLongValue("id");
			client.cancelQueueJobById(id);
		}
		
	}
	@Test
	public void testGetAllJobs(){
		List<String> list = client.getAllJobs();
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testCopyJob(){
		boolean result = client.copyJob("qa_ta_springdemo", "qa_ta_springdemo_copy");
		assertTrue(result);
	}
	
	@Test
	public void testCopyJobAndtrigger(){
		boolean result = client.copyJob("qa_ta_springdemo", "qa_ta_springdemo_copy");
		assertTrue(result);
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("ID_TA_TASK", "000000");
		parameters.put("STAGE_ID", "stage2");
		parameters.put("GROUP_ID", "GROUP_ID");
		boolean trigger = client.triggerJobWithParameters("qa_ta_springdemo_copy",parameters);
		assertTrue(trigger);
	}
	
	@Test
	public void testGetJobConfigXML(){
		String result = client.getJobConfigXML("B2B_FO");
		assertNotNull(result);
	}
	@Test
	public void testGetAndDelete(){
		List<String> jobList = client.getAllJobs();
		assertNotNull(jobList);
		assertTrue(jobList.size() > 0);
		System.out.println(jobList.size());
		for(String jsonStr : jobList){
			assertNotNull(jsonStr);
			JSONObject json = JSON.parseObject(jsonStr);
			String jobName = json.getString("name");
			assertNotNull(jobName);
			if(jobName.startsWith("w")){
				boolean  result = client.deleteJob(jobName);
				assertTrue(result);
				System.out.println("---- --- -- - delete job "+jobName+ " success!");
			}
		}
	}
}
