/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axcc.nspbx.coresocialnet.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String string = "María";
        System.out.println(mysqlSafe(string));
    }
    
    public static String mysqlSafe(String input) {
        if (input == null) return null;
          StringBuilder sb = new StringBuilder();

          for (int i = 0; i < input.length(); i++) {
            if (i < (input.length() - 1)) { // Emojis are two characters long in java, e.g. a rocket emoji is "\uD83D\uDE80";
              if (Character.isSurrogatePair(input.charAt(i), input.charAt(i + 1))) {
                i += 1; //also skip the second character of the emoji
                continue;
              }
            }
            sb.append(input.charAt(i));
          }

        return sb.toString();
    }
    
}
