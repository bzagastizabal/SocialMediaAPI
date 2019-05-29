package org.axcc.nspbx.coresocialnet.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.axcc.nspbx.coresocialnet.model.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "ReceiveMessageChatApi")
public class ReceiveMessageChatApi{
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReceiveMessageChatApi.class);
    private final String instanceId = "43587";
    private final String token = "ovrltuuxo1q7cvgt";
    private final String encoding = "UTF-8";
    @RequestMapping(value = "/ReceiveMessageChatApi", method = RequestMethod.POST)
    @ResponseBody
    public void ReceiveMessageChatApi(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        HttpClient httpClient = new DefaultHttpClient();
        JSONParser parser = new JSONParser();
        
        try (PrintWriter out = response.getWriter()) {
            String url = "http://api.chat-api.com/instance"+instanceId+"/sendMessage?token="+token;
            JSONObject sendPost = new JSONObject();
            
            String msg = request.getParameter("body");
            String hangup = request.getParameter("hangup"); //0 vivo 1 fin
            String uuid = request.getParameter("token");
            if(!hangup.equals("1")){
                Data data = new Data();
                JSONObject clientInformation = data.getClientInformation(uuid);

                String phone_number = clientInformation.get("master-id").toString();

                sendPost.put("phone", phone_number);
                String body = URLDecoder.decode(URLEncoder.encode(msg, encoding));
                sendPost.put("body",body);

                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-type","application/json;charset=UTF-8");

                StringEntity stringEntity = new StringEntity(sendPost.toString());
                httpPost.setEntity(stringEntity);

                HttpResponse httpresponse = httpClient.execute(httpPost);

                /* Get response from Chat-api */
                try {
                    HttpEntity entity = httpresponse.getEntity();
                    String responseString = EntityUtils.toString(entity, encoding);                            
                    JSONObject json = (JSONObject)parser.parse(responseString);
                    json.put("body_encode",URLEncoder.encode(msg, encoding));
                    json.put("body_decode",URLDecoder.decode(msg, encoding));
                    json.put("body",msg);
                    out.print(json);
                } catch (IOException | org.apache.http.ParseException | ParseException ex) {
                    logger.error(ex.getMessage());
                }
            }
                
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
