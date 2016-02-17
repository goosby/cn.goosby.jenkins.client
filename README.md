# com.goosby.jenkins.client
# 一、说明 #

## 1.包含jenkins所有api的调用方法 ##
	创建job，更新job，触发job等共28个远程调用接口，没有包含的请各位完善。
## 2.使用该API前请在项目中引入以下两个jar包。 ##
	<dependency>
		<groupId>org.apache.httpcomponents</groupId>
		<artifactId>httpclient</artifactId>
		<version>4.5.1</version>
	</dependency>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.11</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>fastjson</artifactId>
		<version>1.2.7</version>
	</dependency>
## 3.签出代码后执行下面命令(保证maven库中有上面三个jar包) ##
	mvn package -X
## 4.最后将生成的*com.goosby.jenkins.client-0.0.1-SNAPSHOT.jar*引入到项目中 ##
	<dependency>
		<groupId>com.goosby</groupId>
		<artifactId>com.goosby.jenkins.client</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
# 二、使用 #
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

	public class TestJenkinsClient{
		//jenkins address
	    public static String URL = "http://192.168.138.62:8081/jenkins";
		//jenkins client
		public static JenkinsClient client;
		
		@Beforeclass
		public static viod setUp(){
			client = new JenkinsClient(URL);
		}
		
		@Test
		public void testTriggerJob(){
			boolean result = client.triggerJob("test-git");
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

	}
