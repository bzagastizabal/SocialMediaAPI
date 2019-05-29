package org.axcc.nspbx.coresocialnet.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.axcc.nspbx.coresocialnet.api.model.utils.ChatApi;
import org.axcc.nspbx.coresocialnet.model.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "SendMessageChatApi")
public class SendMessageChatApi {
    
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SendMessageChatApi.class);
    private static String uuid = "";
    private static Long smuserid = null;
    private static PrintWriter out = null;
    @RequestMapping(value = "/SendMessageChatApi", method = RequestMethod.POST)
    @ResponseBody
    public void SendMessageChatApi(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            
            
            JSONParser parser = new JSONParser();
            Data data = new Data();

            /**
             * GET RESPONSE FROM CHAT-API *
             */
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            
            JSONObject json = (JSONObject) parser.parse(jb.toString());
            
            JSONArray jar = (JSONArray) json.get("messages");
            JSONObject messages = (JSONObject) jar.get(0);
            String[] author = messages.get("author").toString().split("@");
            String phone_number = author[0];
            String Body = (String) messages.get("body");
            boolean fromMe = (boolean) messages.get("fromMe");
            SimpleDateFormat format_fecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format_hora = new SimpleDateFormat("hh:mm:ss");
            String fecha = format_fecha.format(new Date());
            String hora = format_hora.format(new Date());
            long unixTime = System.currentTimeMillis() / 1000L;

            //Create new connection with socket
            ChatApi chat_api = new ChatApi();
            if (!fromMe) {
                boolean isNewConnection = data.isNewConnection(phone_number);
                if (isNewConnection) {
                    JSONObject connection = chat_api.createConnection(phone_number);
                    
                    smuserid = (Long) connection.get("smuserid");
                    uuid = (String) connection.get("token");
                    data.saveMessage(uuid, Body, phone_number);
                    try {
                        Thread.sleep(500);
                        /**
                         * Send Message to Asesor *
                         */
                        JSONObject response3 = chat_api.sendMessage(uuid, phone_number, smuserid, Body);
                        
                        try{
                            /*Logger logger = Logger.getLogger("MyLog");
                            FileHandler fh;
                            fh = new FileHandler("D:\\NetBeansProjects\\TwilioWeb\\log\\apache.log");
                            logger.addHandler(fh);
                            SimpleFormatter formatter = new SimpleFormatter();
                            fh.setFormatter(formatter);    */
                            JSONObject obj = new JSONObject();
                            obj.put("Fecha", fecha);
                            obj.put("HoraRecepcion", hora);
                            obj.put("NumeroOrigen", phone_number);
                            obj.put("Mensaje", Body);
                            obj.put("Costo", "0.005");
                            obj.put("DateTimeWic", String.valueOf(unixTime));
                            obj.put("EstadoLectura", 0);
                            obj.put("smuserid", smuserid);
                            obj.put("token", uuid);
                            obj.put("response3", response3);
                            obj.put("message_type", response3.get("message").toString().split(":")[1]);
                            obj.put("newSession", true);

                            //logger.info(json.toJSONString());
                            out.print(obj);
                        } catch (Exception ex) {
                            JSONObject obj = new JSONObject();
                            obj.put("error",ex);
                            out.print(obj);
                            logger.error(ex.getMessage());
                        }
                    } catch (InterruptedException ex) {
                        logger.error(ex.getMessage());
                    }
                } else {
                    JSONObject datos = data.findByPhone(phone_number);
                    uuid = (String) datos.get("token");
                    smuserid = Long.parseLong(datos.get("smuserid").toString());
                    data.saveMessage(uuid, Body, phone_number);
                    
                    try{
                        Thread.sleep(500);
                        JSONObject response3 = chat_api.sendMessage(uuid, phone_number, smuserid, Body);
                        /*Logger logger = Logger.getLogger("MyLog");
                        FileHandler fh;
                        fh = new FileHandler("D:\\NetBeansProjects\\TwilioWeb\\log\\apache.log");  
                        logger.addHandler(fh);
                        SimpleFormatter formatter = new SimpleFormatter();  
                        fh.setFormatter(formatter);    */
                        JSONObject obj = new JSONObject();
                        obj.put("Fecha", fecha);
                        obj.put("HoraRecepcion", hora);
                        obj.put("NumeroOrigen", phone_number);
                        obj.put("Mensaje", Body);
                        obj.put("Costo", "0.005");
                        obj.put("DateTimeWic", String.valueOf(unixTime));
                        obj.put("EstadoLectura", 0);
                        obj.put("smuserid", smuserid);
                        obj.put("token", uuid);
                        obj.put("response3", response3);
                        obj.put("message_type", response3.get("message").toString().split(":")[1]);
                        obj.put("newSession", false);

                        //logger.info(json.toJSONString());
                        out.print(obj);
                    } catch (InterruptedException ex) {
                        JSONObject obj = new JSONObject();
                        obj.put("error",ex);
                        out.print(obj);
                        logger.error(ex.getMessage());
                    }
                }
                data.saveMessage(uuid, Body, phone_number);
            } else {
                logger.info("fromMe: False");
            }
            
        } catch (IOException | ParseException ex) {
            logger.error(ex.getMessage());
        }
        
    }
    
}
