/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.api.service;

import org.axcc.nspbx.coresocialnet.api.common.SystemProperties;
import javax.annotation.PostConstruct;

/**
 *
 * @author BZAGASTIZABAL
 */
public class AsteriskCoreService {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AsteriskCoreService.class);

    @PostConstruct
    public void init() {
        logger.info("PostConstruct");
    }

    public AsteriskCoreService() {
        SystemProperties.initSystemProperties();

        logger.info("");
        logger.info("-------------------------------------------------------");
        logger.info("AsteriskCoreService DIALPLANCREATOR >init");
        logger.info("-------------------------------------------------------");
        logger.info("");
    }
}
