package com.goosby.jenkins.client;

import java.util.HashMap;
import java.util.Map;

import com.goosby.jenkins.httpclient.HttpClient;
import com.goosby.jenkins.httpclient.JenkinsResponse;

public class JenkinsClient {
	
	public static String jenkinsURL;
	
	/**
	 * url格式：http://localhost:8080/jenkins
	 * @param url
	 */
	public JenkinsClient(String url){
		if(url.endsWith("/")){
			jenkinsURL = url.substring(0, url.length()-1);
		}else{
			jenkinsURL = url;
		}
	}
	
	public JenkinsClient(String url,String username,String password){
		if(!url.endsWith("/")){
			jenkinsURL = url.substring(0, url.length()-1);
		}else{
			jenkinsURL = url;
		}
	}
	
	public static void main(String[] args){
		String url = "http://192.168.138.62:8081/jenkins";
		JenkinsClient client = new JenkinsClient(url);
		boolean result = client.abortBuildJob("OB_FGW", 999);
		System.out.println(result);
	}
	
	/**
	 * 创建JOB
	 * jenkinsBaseURL+ "/createItem?name=" + jobName
	 * @param jobName
	 * @param xmlConfig
	 * @return
	 */
	public  boolean createJob(String jobName,String xmlConfig){
		String url = jenkinsURL+"/createItem?name=" + jobName;
		JenkinsResponse response = HttpClient.postBodyWithXML(url,xmlConfig);
		return (200 == response.getResponseCode()) ? true : false;
	};
	
	
	/**
	 * POST 	更新job
	 * jenkinsBaseURL + "/job/"+ jobName + "/config.xml"
	 * @param jobName
	 * @param updateXml
	 * @return
	 */
	public  boolean updateJob(String jobName,String updateXml){
		String url = jenkinsURL + "/job/"+ jobName + "/config.xml";
		JenkinsResponse response = HttpClient.postBodyWithXML(url,updateXml);
		return (200 == response.getResponseCode() )? true : false;
	};
	
	/**
	 * POST
	 * 			复制JOB
	 * jenkinsBaseURL + "/createItem"
	 * NameValuePair n1 = new NameValuePair("name", newJobName);
		NameValuePair n2 = new NameValuePair("mode", "copy");
		NameValuePair n3 = new NameValuePair("from", originJobName);
	 * @param originJobName
	 * @param newJobName
	 * @return
	 */
	public  boolean copyJob(String originJobName, String newJobName){
		return true;
	};
	
	/**
	 * 
	 * @param originJobName
	 * @param newJobName
	 * @return
	 */
	public boolean renameJob(String originJobName, String newJobName){
		String url = jenkinsURL + "/job/" + originJobName + "/doRename";
		Map<String,String> param = new HashMap<String, String>();
		param.put("newName", newJobName);
		JenkinsResponse response = HttpClient.postWithParameters(url, param);
		return (302 == response.getResponseCode()) ? true : false;
	}
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName + "/doDelete"
	 * @param jobName
	 * @return
	 */
	public  boolean deleteJob(String jobName){
		String url = jenkinsURL + "/job/" + jobName + "/doDelete";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * POST		启用项目
	 * jenkinsBaseURL + "/job/"+ jobName + "/enable"
	 * @param jobName
	 * @return
	 */
	public  boolean enableJob(String jobName){
		String url = jenkinsURL + "/job/"+ jobName + "/enable";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * POST    禁用项目
	 * jenkinsBaseURL + "/job/"+ jobName + "/disable"
	 * @param jobName
	 * @return
	 */
	public  boolean disableJob(String jobName){
		String url = jenkinsURL + "/job/"+ jobName + "/disable";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName+ "/build"
	 * @param jobName
	 * @return
	 */
	public  boolean buidlJob(String jobName){
		String url = jenkinsURL+"/job/" + jobName + "/build";
		JenkinsResponse response = HttpClient.postBodyWithXML(url, null);
		return (201 == response.getResponseCode()) ? true : false;
	};
	/**
	 * GET
	 * jenkinsBaseURL + "/job/"+ jobName+ "/buildWithParameters?param1=value1
	 * @param jobName
	 * @param parameters
	 * @return
	 */
	public  boolean buildJobWithParameters(String jobName,Map<String,String> parameters){
		String url = jenkinsURL + "/job/"+ jobName+ "/buildWithParameters";
		JenkinsResponse response = HttpClient.getWithParameters(url, parameters);
		return (200 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * 获取构建信息
	 * @param jobName
	 * @param buildNumber
	 * @return
	 */
	public String getBuildDetails(String jobName, long buildNumber){
		String url = jenkinsURL + "/job/" + jobName + "/" + buildNumber;
		JenkinsResponse response = HttpClient.getWithOutParameter(url);
		return response.getResponseBody();
	}
	
	/**
	 *  检查是否正在构建
	 * @param jobName
	 * @param buildNumber
	 * @return
	 */
	public boolean isBuilding(String jobName,long buildNumber){
		
		return true;
	}
	
	
	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public  boolean abortBuildJob(String jobName,long buildNumber ){
		String url = jenkinsURL + "/job/" + jobName + "/" + buildNumber + "/stop";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	
	/**
	 * GET 	获取jobDetails
	 * 		jenkinsBaseURL + "job/"+ jobName + "/api/json"
	 * @param jobName
	 * @return
	 */
	public  String getJobDetailJSON(String jobName){
		String url = jenkinsURL + "/job/" + jobName + "/api/json";
		JenkinsResponse response = HttpClient.getWithOutParameter(url);
		return response.getResponseBody();
	};
	
	/**
	 * GET  获取jenkins集群信息（主，从信息）
	 * 		jenkinsBaseURL + "computer/api/json"
	 * @return
	 */
	public  String getJenkinsColony(){
		String url = jenkinsURL + "/computer/api/json";
		JenkinsResponse response = HttpClient.getWithOutParameter(url);
		return response.getResponseBody();
	};
	
}
