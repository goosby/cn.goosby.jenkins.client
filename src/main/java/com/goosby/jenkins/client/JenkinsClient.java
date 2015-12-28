package com.goosby.jenkins.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.goosby.jenkins.client.utils.XmlUtil;
import com.goosby.jenkins.httpclient.HttpClient;
import com.goosby.jenkins.httpclient.JenkinsResponse;
import com.goosby.jenkins.model.builddetail.BuildDetail;

public class JenkinsClient {
	
	public static String jenkinsURL;
	
	/**
	 * url格式：	http://localhost:8080/jenkins
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
	
	/**
	 * 创建JOB
	 * 		post 	jenkinsBaseURL+ "/createItem?name=" + jobName
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
	 * 创建view
	 * @param viewName
	 * @return
	 */
	public boolean createView(String viewName){
		String url = jenkinsURL + "/createView";
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", viewName);
		JenkinsResponse response = HttpClient.postWithParameters(url, params);
		return (200 == response.getResponseCode()) ? true : false;
	}
	
	/**
	 * 创建用户
	 * 		POST /securityRealm/createAccount
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean createUser(String userName,String password){
		return true;
	}
	
	/**
	 * POST 	更新（修改）job
	 * jenkinsBaseURL + "/job/"+ jobName + "/config.xml"
	 * @param jobName
	 * @param updateXml
	 * @return
	 */
	public  boolean updateJob(String jobName,String updateXml){
		String url = jenkinsURL + "/job/"+ jobName + "/config.xml";
		JenkinsResponse response = HttpClient.postBodyWithXML(url,updateXml);
		return (200 == response.getResponseCode() ) ? true : false;
	};
	
	/**
	 * POST
	 * 		复制job	jenkinsBaseURL + "/createItem"
	 * @param originJobName
	 * @param newJobName	
	 * @return
	 */
	public  boolean copyJob(String originJobName, String newJobName){
		String url = jenkinsURL + "/createItem";
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", newJobName);
		params.put("mode", "copy");
		params.put("from", originJobName);
		JenkinsResponse response = HttpClient.postWithParameters(url, params);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * 重命名JOB
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
	 * 		删除JOB
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
	 * 	删除view
	 * 		POST jenkinsURL +"/view/"+ viewName + "/doDelete"
	 * @param viewName
	 * @return
	 */
	public boolean deleteView(String viewName){
		String url = jenkinsURL +"/view/"+ viewName + "/doDelete";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	}
	
	/**
	 * 删除user
	 * POST		"/user/" + userName + "/doDelete
	 * @param userName
	 * @return
	 */
	public boolean deleteUser(String userName){
		String url = jenkinsURL + "/user/" + userName + "/doDelete";
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("name", userName);
		JenkinsResponse response = HttpClient.postWithParameters(url, parameters);
		return (200 == response.getResponseCode()) ? true : false;
	}
	
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
	 * 		jenkinsBaseURL + "/job/"+ jobName + "/disable"
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
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
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
	 * 获取指定构建编号JOB的构建信息
	 * @param jobName
	 * @param buildNumber
	 * @return
	 */
	public String getBuildDetails(String jobName, long buildNumber){
		String url = jenkinsURL + "/job/" + jobName + "/" + buildNumber + "/api/json";
		JenkinsResponse response = HttpClient.getWithOutParameter(url);
		return response.getResponseBody();
	}
	/**
	 * 获取正在队列中等待构建的JOB
	 * 		GET 	jenkinsURL + "/queue/api/json"
	 * @return
	 */
	public String getQueueJobItems(){
		String url = jenkinsURL + "/queue/api/json";
		JenkinsResponse response = HttpClient.getWithOutParameter(url);
		return response.getResponseBody();
	}
	
	
	/**
	 * GET
	 * 	取消任务队列（排队）中的job
	 * /queue/cancelItem?id=" + itemId
	 * @param itemId
	 * @return
	 */
	public boolean cancelQueueJobByid(long id){
		String url = jenkinsURL + "/queue/cancelItem";
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("id", String.valueOf(id));
		JenkinsResponse response = HttpClient.getWithParameters(url, parameters);
		return (200 == response.getResponseCode()) ? true : false;
	}
	
	/**
	 *  检查job是否正在构建
	 * @param jobName
	 * @param buildNumber
	 * @return
	 */
	public boolean isBuilding(String jobName,long buildNumber){
		String result = this.getBuildDetails(jobName, buildNumber);
		BuildDetail buildDetail = JSON.parseObject(result, BuildDetail.class);
		return buildDetail.getBuilding();
	}
	
	
	/**
	 * 停止正在构建的job
	 * @param jobName
	 * @return
	 */
	public  boolean abortBuildJob(String jobName,long buildNumber ){
		String url = jenkinsURL + "/job/" + jobName + "/" + buildNumber + "/stop";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return (302 == response.getResponseCode()) ? true : false;
	};
	
	/**
	 * 获取jenkins的信息
	 * POST		"／api/json"
	 * @return
	 */
	public String getJenkinsApiXml(){
		String url = jenkinsURL + "/api/xml";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return response.getResponseBody();
	}
	
	/**
	 * 获取jenkins的信息
	 * 	GET 	jenkinsURL + "/api/json"
	 * @return
	 */
	public String getJenkinsApiJson(){
		String url = jenkinsURL + "/api/json";
		JenkinsResponse response = HttpClient.postWithOutParameters(url);
		return response.getResponseBody();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAllJobs(){
		return XmlUtil.parseJobs(this.getJenkinsApiXml());
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAllViews(){
		return XmlUtil.parseViews(getJenkinsApiXml());
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAllUsers(){
		return XmlUtil.parseUsers(getJenkinsApiXml());
	}
	
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
