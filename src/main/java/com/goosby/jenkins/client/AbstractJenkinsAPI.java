package com.goosby.jenkins.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goosby.jenkins.model.JobStatus;
import com.goosby.jenkins.model.JobStatus.Status;

public abstract class AbstractJenkinsAPI {
	
	/**
	 * GET
	 * 从HTTP RESPONSE 的header中获取是否有 X-Hudson
	 * @param jenkinsUrl
	 * @return
	 */
	public abstract boolean isJenkinsUrl(String jenkinsUrl);
	
	/**
	 * 
	 * GET
	 * @return
	 */
	public abstract String getJenkinsVersion();
	
	/**
	 * jenkinsBaseURL+ "/createItem?name=" + jobName
	 * @param jobName
	 * @param xmlConfig
	 * @return
	 */
	public abstract boolean createJob(String jobName,String xmlConfig);
	
	/**
	 * POST
	 * @param jobName
	 * @param jobXml
	 * @param crumbFlag
	 * @return
	 */
	public abstract boolean createJob(String jobName, String jobXml, Boolean crumbFlag);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName + "/config.xml"
	 * @param jobName
	 * @param updateXml
	 * @return
	 */
	public abstract boolean updateJob(String jobName,String updateXml);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/createItem"
	 * NameValuePair n1 = new NameValuePair("name", newJobName);
		NameValuePair n2 = new NameValuePair("mode", "copy");
		NameValuePair n3 = new NameValuePair("from", originJobName);
	 * @param originJobName
	 * @param newJobName
	 * @return
	 */
	public abstract boolean copyJob(String originJobName, String newJobName);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName + "/doDelete"
	 * @param jobName
	 * @return
	 */
	public abstract boolean deleteJob(String jobName);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName + "/enable"
	 * @param jobName
	 * @return
	 */
	public abstract boolean enableJob(String jobName);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName + "/disable"
	 * @param jobName
	 * @return
	 */
	public abstract boolean disableJob(String jobName);
	
	/**
	 * POST
	 * jenkinsBaseURL + "/job/"+ jobName+ "/build"
	 * @param jobName
	 * @return
	 */
	public abstract String buidlJob(String jobName);
	/**
	 * GET
	 * jenkinsBaseURL + "/job/"+ jobName+ "/buildWithParameters?param1=value1
	 * @param jobName
	 * @param parameters
	 * @return
	 */
	public abstract String buildJobWithParameters(String jobName,Map<String,String> parameters);
	
	/**
	 * jenkinsBaseURL + "/job/"+ jobName+ "/lastSuccessfulBuild/buildTimestamp?format=dd/MM/yyyy"
	 * @param jobName
	 * @return
	 */
	public abstract String getLastSuccessfullBuild(String jobName);
	
	/**
	 * GET
	 * jenkinsBaseURL + "/api/xml"
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 * @throws DocumentException
	 */
	public List<JobStatus> readJobStatus() throws IOException, HttpException,DocumentException {
		List<JobStatus> jobStatus = new ArrayList<JobStatus>();
		URL url = new URL("" + "/api/xml");
		Document dom = new SAXReader().read(url);
		Element element = dom.getRootElement();
		if(element == null){
			return null;
		}
		List<Element> elementList = element.elements("job");
		if(null == elementList || elementList.size() < 1){
			return null;
		}
		for (Element job : elementList) {
			jobStatus.add(new JobStatus(job.elementText("name"), Status.create(job.elementText("color"))));
		}
		return jobStatus;
	}
	
	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public abstract String abortBuildJob(String jobName);
	
	/**
	 * 
	 * @param jobName
	 * @param buildNumber
	 * @return
	 */
	public abstract String abortBuilJob(String jobName,long buildNumber);
	
	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public abstract String getJobDetailJSON(String jobName);
	
	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public abstract String getJobDetailXML(String jobName);
	
	
}
