package com.goosby.jenkins.client.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goosby@outlook.com
 */
public class XmlUtil {

    public static List<String> parseJobs(String xmlContent) {
        return parseXmlPerItem("job", xmlContent);
    }

    public static List<String> parseUsers(String xmlContent) {
        return parseXmlPerItem("user", xmlContent);
    }

    public static List<String> parseViews(String xmlContent) {
        List<String> views= parseXmlPerItem("view", xmlContent);
        List<String> result = new ArrayList<>();
        for (String view : views) {
            if (!"all".equals(view.toLowerCase())) {
                result.add(view);
            }
        }
        return result;
    }

    private static List<String> parseXmlPerItem(String item, String xmlContent) {
        List<String> list = new ArrayList<>();
        String[] jobs = xmlContent.split(item + ">");
        for (String job : jobs) {
            String[] names = job.split("name>");
            if (names.length == 3) {
                String name = names[1];
                name = name.substring(0, name.length() - 2);
                list.add(name);
            }
        }
        return list;
    }
}
