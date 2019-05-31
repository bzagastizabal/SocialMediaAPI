package org.axcc.nspbx.coresocialnet.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.axcc.nspbx.coresocialnet.api.model.utils.ChatApi;
import org.axcc.nspbx.coresocialnet.model.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "ReceiveMessageChatApi")
public class ReceiveMessageChatApi {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReceiveMessageChatApi.class);
    private final String instanceId = "44250";
    private final String token = "3kzwe0xbgdrhcgkf";
    private final String encoding = "UTF-8";
    PrintWriter out;

    @RequestMapping(value = "/ReceiveMessageChatApi", method = RequestMethod.GET)
    @ResponseBody
    public void ReceiveMessageChatApi(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            request.setCharacterEncoding("UTF-8");
            JSONParser parser = new JSONParser();
            
            try {
                out = response.getWriter();
                String url = "http://api.chat-api.com/instance" + instanceId + "/sendMessage?token=" + token;
                JSONObject sendPost = new JSONObject();
                Data data = new Data();
                
                String msg = request.getParameter("body");
                
                //0 vivo
                //1 fin
                String hangup = request.getParameter("hangup");
                String uuid = request.getParameter("token");
                
                String phone_number;
                if (!hangup.equals("1")) {
                    JSONObject clientInformation = data.getClientInformation(uuid);
                    
                    phone_number = clientInformation.get("master-id").toString();
                    if (msg.equals("TIMEOUT")) {
                        ChatApi chatapi = new ChatApi();
                        chatapi.endSession(uuid, phone_number, data.getReceiver(uuid), msg);
                    }else{
                        sendPost.put("phone", phone_number);
                        sendPost.put("body", URLDecoder.decode(URLEncoder.encode(msg,encoding)));
                        
                        HttpPost httpPost = new HttpPost(url);
                        httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
                        
                        StringEntity stringEntity = new StringEntity(sendPost.toString());
                        httpPost.setEntity(stringEntity);
                        
                        HttpResponse httpresponse = httpClient.execute(httpPost);
                        //data.saveMessage(uuid, URLDecoder.decode(msg, encoding), phone_number, 0);
                        /* Get response from Chat-api */
                        try {
                            HttpEntity entity = httpresponse.getEntity();
                            String responseString = EntityUtils.toString(entity,encoding);
                            JSONObject json = (JSONObject) parser.parse(responseString);
                            json.put("body", msg);
                            json.put("body2", URLEncoder.encode(msg, encoding));
                            out.print(json);
                        } catch (IOException | org.apache.http.ParseException | org.json.simple.parser.ParseException ex) {
                            JSONObject obj = new JSONObject();
                            obj.put("body", msg);
                            obj.put("body2", URLEncoder.encode(msg, encoding));
                            obj.put("error", ex);
                            out.print(obj);
                            logger.error(ex.getMessage());
                        }
                    }
                    
                } else {
                    JSONObject obj = new JSONObject();
                    obj.put("body", msg);
                    out.print(obj);
                }
                
            } catch (Exception ex) {
                JSONObject obj = new JSONObject();
                obj.put("error", ex);
                out.print(obj);
                logger.error(ex.getMessage());
            }
        } catch (UnsupportedEncodingException ex) {
            JSONObject obj = new JSONObject();
            obj.put("error", ex);
            out.print(obj);
            logger.error(ex.getMessage());
        }
    }
}
