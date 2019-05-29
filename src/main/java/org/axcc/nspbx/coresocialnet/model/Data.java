/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.model;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Admin
 */
public class Data extends JdbcTemplate {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Data.class);

    private DriverManagerDataSource basicdataSource;

    public Data() {
        basicdataSource = new DriverManagerDataSource();
        basicdataSource.setUrl("jdbc:mysql://localhost/aaccmod");
        basicdataSource.setUsername("root");
        basicdataSource.setPassword("sysserver02");
        basicdataSource.setDriverClassName("com.mysql.jdbc.Driver");
        this.setDataSource(basicdataSource);
    }

    public JSONObject getClientInformation(String token) {
        try {
            JSONObject json = new JSONObject();
            JSONParser parser = new JSONParser();

            String sql = "SELECT clientInformation FROM smtbusersession WHERE uuid='" + token + "'";

            String clientInformation = this.queryForObject(sql, String.class);
            json = (JSONObject) parser.parse(clientInformation);

            return json;
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public boolean isNewConnection(String phone) {
        boolean isNew = false;
        JSONObject json = new JSONObject();
        json.put("master-id", phone);
        String sql = "SELECT endSession FROM smtbusersession WHERE clientInformation='" + json + "' AND endSession is null";
        int cont = 0;
        cont = this.queryForList(sql).size();
        if (cont == 0) {
            isNew = true;
        }
        return isNew;
    }

    public JSONObject findByPhone(String phone_number) {
        JSONObject obj = new JSONObject();
        JSONObject clientInformation = new JSONObject();
        clientInformation.put("master-id", phone_number);
        String sql = "SELECT uuid,userIdSession FROM smtbusersession WHERE clientInformation='" + clientInformation + "' AND endSession is null";

        Map<String, Object> queryForMap = this.queryForMap(sql);
        obj.put("smuserid", queryForMap.get("userIdSession"));
        obj.put("token", queryForMap.get("uuid"));
        return obj;
    }

    public boolean saveMessage(String token, String message, String phone) {
        boolean saved = false;
        String sql = "INSERT INTO tbmessage (body,token,phone) VALUES (?,?,?)";
        int cont = this.update(sql, new Object[]{token, message, phone});
        if (cont == 1) {
            saved = true;
        }
        return saved;
    }

    public Long getReceiver(String token) {
        Long receiver = 0L;
        String sql = "SELECT userIdTwo FROM smchatchannel WHERE uuid='" + token + "'";
        receiver = this.queryForObject(sql, Long.class);
        return receiver;
    }
}
