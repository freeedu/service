package org.personal.mason.feop.oauth.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String... args) {
        try {
            String encode = URLEncoder.encode("http://localhost:8999/contact/contact/q/kw?accountId=1&query=m", "UTF-8");
            System.out.print(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
