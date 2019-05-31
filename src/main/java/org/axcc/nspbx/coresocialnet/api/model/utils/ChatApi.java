/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.model.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.axcc.nspbx.coresocialnet.model.Data;
import org.axcc.nspbx.coresocialnet.model.ReadResponse;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
public class ChatApi {

    private final String host = "localhost";
    private final String port = "8090";
    Data data;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ChatApi.class);

    public JSONObject sendMessage(String token, String phone_number, Long smuserid, String body) {
        JSONObject obj = new JSONObject();
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpresponse;
        data = new Data();
        try {
            String uuid = token;
            String master_id = phone_number;
            Long sender = smuserid;
            Long receiver = data.getReceiver(token);
            String time = String.valueOf((System.currentTimeMillis() / 1000L));

            //Make url with GET params
            URIBuilder builder = new URIBuilder("http://" + host + ":"+port+"/coreservices/pages/api/chat/exMessage");
            builder.setParameter("uuid", uuid);
            builder.setParameter("master-id", master_id);
            builder.setParameter("sender", String.valueOf(sender));
            builder.setParameter("receiver", String.valueOf(receiver));
            builder.setParameter("time", time);
            builder.setParameter("message", body);

            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "es-ES,es;q=0.9,en;q=0.8");
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
            httpresponse = httpClient.execute(httpGet);

            ReadResponse reader = new ReadResponse();
            String sb = reader.readResponse(httpresponse);
            obj = new JSONObject(sb);
            obj.put("receiver", receiver);

        } catch (URISyntaxException ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        }
        return obj;
    }

    public JSONObject createConnection(String phone_number) {
        JSONObject obj = new JSONObject();
        try {
            JSONObject queryJSON = new JSONObject();
            JSONObject clientInformation = new JSONObject();
            clientInformation.put("master-id", phone_number);
            queryJSON.put("socialmediaID", "7e832592-3814-46de-968f-de059f3a8134");
            queryJSON.put("tag", "WSP");
            queryJSON.put("clientInformation", clientInformation.toString());

            String query = queryJSON.toString();
            String url = "http://" + host + ":8080/coreservices/pages/api/user/requesting/createSMUserEx";
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate");
            httpPost.setHeader("Accept-Language", "es-ES,es;q=0.9,en;q=0.8");
            httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
            StringEntity stringEntity = new StringEntity(query);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            HttpResponse httpresponse = httpClient.execute(httpPost);

            ReadResponse reader2 = new ReadResponse();
            String sb = reader2.readResponse(httpresponse);
            logger.info(sb);
            JSONObject response2 = new JSONObject(sb);
            Long smuserid = response2.getLong("id");
            String uuid = response2.getString("uuid");
            obj.put("smuserid", smuserid);
            obj.put("uuid", uuid);
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return obj;
    }
    
    public JSONObject endSession(String token, String phone_number, Long smuserid, String body) {
        JSONObject obj = new JSONObject();
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpresponse;
        data = new Data();
        try {
            String uuid = token;
            String master_id = phone_number;
            Long sender = smuserid;
            Long receiver = data.getReceiver(token);
            String time = String.valueOf((System.currentTimeMillis() / 1000L));

            //Make url with GET params
            URIBuilder builder = new URIBuilder("http://" + host + ":8080/coreservices/pages/api/chat/exHangup");
            builder.setParameter("uuid", uuid);
            builder.setParameter("master-id", master_id);
            builder.setParameter("sender", String.valueOf(sender));
            builder.setParameter("receiver", String.valueOf(receiver));
            builder.setParameter("time", time);
            builder.setParameter("message", body);

            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "es-ES,es;q=0.9,en;q=0.8");
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
            httpresponse = httpClient.execute(httpGet);

            ReadResponse reader = new ReadResponse();
            String sb = reader.readResponse(httpresponse);
            obj = new JSONObject(sb);
            obj.put("receiver", receiver);

        } catch (URISyntaxException ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            obj.put("error",ex);
            logger.error(ex.getMessage());
        }
        return obj;
    }
    
    public org.json.simple.JSONObject deleteInstance(int id){
        org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
        Data data = new Data();
        
        return obj;
    }
}
