/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class DeleteInstance{

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SendMessageChatApi.class);
    private static String uuid = "";
    private static Long smuserid = null;
    private static PrintWriter out = null;
    @RequestMapping(value = "/DeleteInstance", method = RequestMethod.POST)
    @ResponseBody
    public void DeleteInstance(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        processRequest(request, response);
    }
    
    @RequestMapping(value = "/UpdateInstance", method = RequestMethod.POST)
    @ResponseBody
    public void UpdateInstance(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try{
            out = response.getWriter();
            
        }catch(Exception ex){
            logger.error(ex);
        }
    }


}
