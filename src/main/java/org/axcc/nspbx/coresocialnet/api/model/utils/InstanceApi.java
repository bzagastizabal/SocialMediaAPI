/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.model.utils;

/**
 *
 * @author Admin
 */
public class InstanceApi {
    public int instanceId;
    public String phone_number;
    public String webhook;
    public String token;
    public int skill_id;
    public String aacc;
    public int idEngineDial;
    public String custom_parameters;
    public String socialmediaID;

    public InstanceApi() {
    }
    
    

    public InstanceApi(int instanceId, String phone_number, String webhook, String token, int skill_id, String aacc, int idEngineDial, String custom_parameters, String socialmediaID) {
        this.instanceId = instanceId;
        this.phone_number = phone_number;
        this.webhook = webhook;
        this.token = token;
        this.skill_id = skill_id;
        this.aacc = aacc;
        this.idEngineDial = idEngineDial;
        this.custom_parameters = custom_parameters;
        this.socialmediaID = socialmediaID;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public String getAacc() {
        return aacc;
    }

    public void setAacc(String aacc) {
        this.aacc = aacc;
    }

    public int getIdEngineDial() {
        return idEngineDial;
    }

    public void setIdEngineDial(int idEngineDial) {
        this.idEngineDial = idEngineDial;
    }

    public String getCustom_parameters() {
        return custom_parameters;
    }

    public void setCustom_parameters(String custom_parameters) {
        this.custom_parameters = custom_parameters;
    }

    public String getSocialmediaID() {
        return socialmediaID;
    }

    public void setSocialmediaID(String socialmediaID) {
        this.socialmediaID = socialmediaID;
    }
    
    
}
