/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.axcc.nspbx.coresocialnet.api.controller.ReceiveMessageChatApi;

/**
 *
 * @author Admin
 */
public class SqlConnection {
    private final String user = "wimprove";
    private final String psw = "w1mpr0v3";
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReceiveMessageChatApi.class);
    public Connection createConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.
                getConnection("jdbc:mysql://54.39.1.25:3306/aaccmod",user,psw);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
        }
        
        return con;
    }
    
}
