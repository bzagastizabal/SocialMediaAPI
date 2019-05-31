/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.axcc.nspbx.coresocialnet.api.model.utils.ChatApi;
import org.axcc.nspbx.coresocialnet.api.model.utils.InstanceApi;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
public class CrudInstanceApi extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SendMessageChatApi.class);
    private static String uuid = "";
    private static Long smuserid = null;
    private static PrintWriter out = null;
    
    @RequestMapping(value = {"instance/delete"}, method = RequestMethod.GET)
    @ResponseBody
    public String DeleteInstance(
            @RequestParam(value = "id", required = true)Integer id,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        ChatApi chatapi = new ChatApi();
        JSONObject delete = chatapi.deleteInstance(id);
        
        return delete.toString();
    }
    
    @RequestMapping(value = {"instance/update"}, method = RequestMethod.GET)
    @ResponseBody
    public void UpdateInstance(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
    }
    
    @RequestMapping(value = {"instance/create"}, method = RequestMethod.GET)
    @ResponseBody
    public void CreateInstance(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
    }
    
    @RequestMapping(value = {"instance/list"}, method = RequestMethod.GET)
    @ResponseBody
    public void GetInstance(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
    }

}
