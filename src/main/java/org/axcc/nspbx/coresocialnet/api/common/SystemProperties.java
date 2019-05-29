/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author BZAGASTIZABAL
 */
public class SystemProperties {
    	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SystemProperties.class);
	
    public static String APIRECORD_NAMEFILECUSTOM;
    public static String APIRECORD_NAMEFORMATDATE;
    public static String APIRECORD_PATHFILE;
    public static String APIRECORD_EXTENSIONFILE;
    public static String APIRECORD_DIRECTORYTREEFORMATDATE;
    public static String APILOGIN_CODETIME_LOGGEDOUT;
    public static String APILOGIN_CODETIME_LOGGED;
    public static String APILOGIN_CODETIME_BREAK;
    public static String APILOGIN_CODETIME_ADM;
    public static String APIDAO_DRIVERDB;
    public static String APIDAO_USERDB;
    public static String APIDAO_PASSDB;
    public static String APIDAO_HOSTDB;
    public static String APIDAO_NAMEDB;
    public static String APIDAO_PORTDB;
    public static String AMI_USERASTER;
    public static String AMI_PASSASTER;
    public static String AMI_HOSTASTER;
    public static String AMI_VERASTER;
    public static String AMI_PORTASTER;
    public static String MWD_HOSTMWD;
    public static String MWD_PORTMWD;
    public static String MWD_VERMWD;
    public static String FTP_HOSTFTP;
    public static String FTP_USERFTP;
    public static String FTP_PASSFTP;
    public static String FTP_LOCALDIRFTP;
    public static String FTP_REMOTEDIRFTP;
    public static String FAX_PATHFILE;
    public static String FAX_EXTENSIONFILE;
    public static String ASTERISK_TRANSFERCONTEXT;
    public static final String APIENGINE_PROGRESSIVE = "PROGRESIVE";
    public static String APIENGINE_PROGRESSIVE_CONTEXT;
    public static String APIENGINE_EXTEN_CONTEXT;
    public static String DEFAULT_EXTEN_CONTEXT="extensions";
    
    public static String APIENGINE_PREDICTIVE_CONTEXT;
    public static String APIENGINE_APPANNOUNCE_CONTEXT;
    public static final String APIENGINE_PRED_CIRCULAR = "CIRCULAR";
    public static final String APIENGINE_PRED_ABILITY = "ABILITY";
    public static final String APIENGINE_PRED_LOADBALANCING = "LOADBALANCING";
    public static final String APIENGINE_PRED_LOADHIGH = "LOADHIGH";
    public static final int ENGINE_THREAD_COUNT = 5;
    public static final Long ENGINE_KEEP_ALIVE;
    public static final int ENGINE_CORE_POOL_SIZE = 5;
    public static final TimeUnit ENGINE_UNIT_TIME;
    public static final int LISTENER_THREAD_COUNT = 1;
    public static final Long LISTENER_KEEP_ALIVE;
    public static final int LISTENER_CORE_POOL_SIZE = 1;
    public static final TimeUnit LISTENER_UNIT_TIME;
    public static String AUDIO_LOGIN_AGENT;
    public static String AUDIO_LOGIN_PASSWORD;
    public static String AUDIO_LOGIN_PASSWORDNONOK;
    public static String AUDIO_LOGIN;
    public static String AUDIO_PASS;
    private static String AUDIO_LOGIN_OK;
    private static String AUDIO_LOGIN_AGENTNONOK;
    private static String AUDIO_LOGIN_AGENTBUSSY;
    private static String AUDIO_LOGOUT_OK;
    private static String AUDIO_LOGOUT_NONLOGGED;
    private static String AUDIO_LOGINOTHER_OKIN;
    private static String AUDIO_LOGINOTHER_OKOUT;
    private static String AUDIO_LOGINOTHER_ERROR;
    public static String AMD_SETTINGS;
    private static ConcurrentHashMap<String, String> VARIABLESGLOBAL;
    public static ConcurrentHashMap<Integer, String> SOUNDSPATH;
  
    public static String AST_SOUNDPATH;
    public static String AST_SOUNDLANGUAGE;

    public static String DF_AMD_INITIALSILENCE;
    public static String DF_AMD_GREETING;
    public static String DF_AMD_AFTERGREETINGSILENCE;
    public static String DF_AMD_TOTALANALYSISTIME;
    public static String DF_AMD_MINIMUMWORDLENGTH;
    public static String DF_AMD_BETWEENWORDSSILENCE;
    public static String DF_AMD_MAXIMUMNUMBEROFWORDS;
    public static String DF_AMD_SILENCETHRESHOLD;
    
    
    protected static boolean initSystemProperties__ =false;
    public static void initSystemProperties() {
        if (!initSystemProperties__) {
        	
        	initSystemProperties__=true;
        	logger.info("initSystemProperties() >init");
        	
            APIRECORD_NAMEFORMATDATE = UtilsProperties.getPropertyValue("ApiRecordNameFormatDate", false);
            APIRECORD_PATHFILE = UtilsProperties.getPropertyValue("ApiRecordPathFile", false);
            APIRECORD_EXTENSIONFILE = UtilsProperties.getPropertyValue("ApiRecordExtension", false);
            APIRECORD_DIRECTORYTREEFORMATDATE = UtilsProperties.getPropertyValue("ApiRecordTreeDirectoryFormatDate", false);
            ASTERISK_TRANSFERCONTEXT = UtilsProperties.getPropertyValue("TransferContext", false);
            
            APIDAO_DRIVERDB = UtilsProperties.getPropertyValue("Connection-db-driver", false);
            APIDAO_USERDB = UtilsProperties.getPropertyValue("Connection-db-username", false);
            APIDAO_PASSDB = UtilsProperties.getPropertyValue("Connection-db-password", false);
            APIDAO_HOSTDB = UtilsProperties.getPropertyValue("Connection-db-hostname", false);
            APIDAO_NAMEDB = UtilsProperties.getPropertyValue("Connection-db-database", false);
            APIDAO_PORTDB = UtilsProperties.getPropertyValue("Connection-db-port", false);
            
            AMI_USERASTER = UtilsProperties.getPropertyValue("Connection-asterisk-username", false);
            AMI_PASSASTER = UtilsProperties.getPropertyValue("Connection-asterisk-password", false);
            AMI_HOSTASTER = UtilsProperties.getPropertyValue("Connection-asterisk-hostname", false);
            AMI_VERASTER = UtilsProperties.getPropertyValue("Connection-asterisk-version", false);
            AMI_PORTASTER = UtilsProperties.getPropertyValue("Connection-asterisk-port", false);
            
            MWD_HOSTMWD = UtilsProperties.getPropertyValue("Connection-axcc-hostname", false);
            MWD_PORTMWD = UtilsProperties.getPropertyValue("Connection-axcc-port", false);
            MWD_VERMWD = UtilsProperties.getPropertyValue("Connection-axcc-ver", false);
            APIENGINE_PROGRESSIVE_CONTEXT = UtilsProperties.getPropertyValue("Progressive-context", false).trim();
            APIENGINE_PREDICTIVE_CONTEXT = UtilsProperties.getPropertyValue("Predictive-context", false).trim();
            APIENGINE_APPANNOUNCE_CONTEXT = UtilsProperties.getPropertyValue("App-announce-context", false).trim();
            APIENGINE_EXTEN_CONTEXT = UtilsProperties.getPropertyValue("Exten-context", false).trim();
            
            FTP_HOSTFTP = UtilsProperties.getPropertyValue("Connection-ftp-host", false).trim();
            FTP_USERFTP = UtilsProperties.getPropertyValue("Connection-ftp-username", false).trim();
            FTP_PASSFTP = UtilsProperties.getPropertyValue("Connection-ftp-password", false).trim();
            FTP_LOCALDIRFTP = UtilsProperties.getPropertyValue("Connection-ftp-localDirectory", false).trim();
            FTP_REMOTEDIRFTP = UtilsProperties.getPropertyValue("Connection-ftp-remoteDirectory", false).trim();
            
            FAX_PATHFILE = UtilsProperties.getPropertyValue("ApiFaxPathFile", false).trim();
            FAX_EXTENSIONFILE = UtilsProperties.getPropertyValue("ApiFaxExtensionsFile", false).trim();
            APILOGIN_CODETIME_LOGGED = UtilsProperties.getPropertyValue("LoginPattern", false).trim();
            APILOGIN_CODETIME_LOGGEDOUT = UtilsProperties.getPropertyValue("LogoutPattern", false).trim();
            APILOGIN_CODETIME_BREAK = UtilsProperties.getPropertyValue("LogbreakPattern", false).trim();
            APILOGIN_CODETIME_ADM = UtilsProperties.getPropertyValue("LogadmPattern", false).trim();
            
            AST_SOUNDPATH = UtilsProperties.getPropertyValue("ast_soundpath", false);
            AST_SOUNDLANGUAGE = UtilsProperties.getPropertyValue("ast_soundlang", false);
            
            logger.info("Load AMD parameters");
            DF_AMD_INITIALSILENCE = UtilsProperties.getPropertyValue("amd-initialsilence", false).trim();
            DF_AMD_GREETING = UtilsProperties.getPropertyValue("amd-greeting", false).trim();
            DF_AMD_AFTERGREETINGSILENCE = UtilsProperties.getPropertyValue("amd-aftergreetingsilence", false).trim();
            DF_AMD_TOTALANALYSISTIME = UtilsProperties.getPropertyValue("amd-totalanalysistime", false).trim();
            DF_AMD_MINIMUMWORDLENGTH = UtilsProperties.getPropertyValue("amd-minimumwordlength", false).trim();
            DF_AMD_BETWEENWORDSSILENCE = UtilsProperties.getPropertyValue("amd-betweenwordssilence", false).trim();
            DF_AMD_MAXIMUMNUMBEROFWORDS = UtilsProperties.getPropertyValue("amd-maximumnumberofwords", false).trim();
            DF_AMD_SILENCETHRESHOLD = UtilsProperties.getPropertyValue("amd-silencethreshold", false).trim();

            
            logger.info("initSystemProperties() <done");
        }
    }

    public static ConcurrentHashMap getVariablesGlobal() {
        return VARIABLESGLOBAL;
    }

    public static String addVariablesGlobal(String key, String value) {
        return VARIABLESGLOBAL.put(key, value);
    }

    public static boolean remVariableGlobal(String key) {
        if (VARIABLESGLOBAL.containsKey(key)) {
            VARIABLESGLOBAL.remove(key);
            return true;
        }
        return false;
    }

    public static String getVariableGlobal(String key) {
        return VARIABLESGLOBAL.get(key);
    }

    static {
        ENGINE_KEEP_ALIVE = 1L;
        ENGINE_UNIT_TIME = TimeUnit.SECONDS;
        LISTENER_KEEP_ALIVE = 1L;
        LISTENER_UNIT_TIME = TimeUnit.SECONDS;
        AUDIO_LOGIN_AGENT = "agent-user";
        AUDIO_LOGIN_PASSWORD = "agent-pass";
        AUDIO_LOGIN_PASSWORDNONOK = "agent-inco";
        AUDIO_LOGIN = "agent-user";
        AUDIO_PASS = "agent-pass";
        AUDIO_LOGIN_OK = "agent-loginok";
        AUDIO_LOGIN_AGENTNONOK = "agent-inco";
        AUDIO_LOGIN_AGENTBUSSY = "agent-already";
        AUDIO_LOGOUT_OK = "agent-loggedoff";
        AUDIO_LOGOUT_NONLOGGED = "agent-nologged";
        AUDIO_LOGINOTHER_OKIN = "agent-tempoff";
        AUDIO_LOGINOTHER_OKOUT = "agent-tempon";
        AUDIO_LOGINOTHER_ERROR = "agent-optionincorrect";
        AMD_SETTINGS = "";
        VARIABLESGLOBAL = new ConcurrentHashMap();
		
		initSystemProperties();
        
    }
}
