/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

/**
 *
 * @author Admin
 */
public class ReadResponse {
    public String readResponse(HttpResponse httpresponse) throws IOException, Exception{
        InputStream ips = httpresponse.getEntity().getContent();
        BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
        if (httpresponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new Exception(httpresponse.getStatusLine().getReasonPhrase());
        }
        StringBuilder sb = new StringBuilder();
        String s;
        while (true) {
            s = buf.readLine();
            if (s == null || s.length() == 0) {
                break;
            }
            sb.append(s);

        }
        buf.close();
        ips.close();
        return sb.toString();
    }
    
}
