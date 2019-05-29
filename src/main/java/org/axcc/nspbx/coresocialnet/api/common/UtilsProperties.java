/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.common;

import java.io.FileInputStream;

/**
 *
 * @author BZAGASTIZABAL
 */
public class UtilsProperties {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UtilsProperties.class);
    static java.util.Properties tmpProperties = null;

    public static String getPropertyValue(String aPropertyName, boolean aRaise) {
        try {
            if (tmpProperties == null) {
                java.util.Properties tmpProperties_tmp = new java.util.Properties();
                String agiproxy_conf = "agiproxy.conf";
                String agiproxy_path = System.getProperty("catalina.home");
                String agiproxy_full = agiproxy_path + "/" + agiproxy_conf;

                logger.info("------------------------------------------------");
                logger.info("CATALINA_HOME=" + agiproxy_path);
                logger.info("search agiproxy.conf from path: " + agiproxy_full);
                logger.info("------------------------------------------------");
                java.io.File f = new java.io.File(agiproxy_full);
                if (!f.exists()) {
                    logger.error("file with system properties filepath=" + agiproxy_full + " not FOUND");
                    return "";
                }
                logger.info("load file with system properties filepath=" + agiproxy_full);
                tmpProperties_tmp.load(new FileInputStream(agiproxy_full));
                tmpProperties = tmpProperties_tmp;
            }
            String tmpPropertyValue = tmpProperties.getProperty(aPropertyName);
            if ((tmpPropertyValue == null || tmpPropertyValue.isEmpty()) && aRaise) {
                throw new Exception(aPropertyName + " is empty");
            }
            if (tmpPropertyValue == null) {
                tmpPropertyValue = "";
            }
            return tmpPropertyValue;
        } catch (Exception ex) {
            logger.info("error load file with system properties -> " + ex.getMessage(), ex);
            return "";
        }
    }
}
