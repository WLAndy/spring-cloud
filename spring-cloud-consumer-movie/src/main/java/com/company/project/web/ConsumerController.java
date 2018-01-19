package com.company.project.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 测试方法
     * @return
     */
    @RequestMapping("/test")
    public String testController(){
        return "isOK";
    }

    /**
     * 远程调用User微服务
     * @param id
     * @return
     */
    @RequestMapping("/findByIdIsString/{id}")
    public Result findByIdRequestMethodString(@PathVariable Long id){
        //服务提供者的application name
        String url = "http://provider-user/";
        //请求的接口
        String methodName = "userinfo/detail?id="+id;
        url = url + methodName;
        //发送请求得到数据
        Result result = restTemplate.getForObject(url,Result.class);
        return result;
    }

    /**
     * 远程调用User微服务
     * @param id
     * @return
     */
    @RequestMapping("/findByIdIsStringPost/{id}")
    public Result findByIdRequestMethodStringPost(@PathVariable Long id, HttpServletRequest request){
        String url = "http://provider-user/";
        String methodName = "userinfo/detail";
        url = url + methodName;
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json;charet=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Accept",MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(jsonObject), httpHeaders);
        Result result = restTemplate.postForObject(url,formEntity,Result.class);
        return result;
    }
}
