package org.personal.mason.feop.oauth.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/3/13
 * Time: 2:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    private static String SERVICE_REGEX = ".*://[^/]*/";
    private static Pattern SERVICE_PATTERN = Pattern.compile(SERVICE_REGEX);

    public static String getPrefix(String authUrl) {
        Matcher matcher = SERVICE_PATTERN.matcher(authUrl);
        if(matcher.find()){
            return matcher.group();
        }
        return authUrl;
    }

    public static void main(String... args)
    {
        System.out.print(getPrefix("http://wew.wer.wre/fsfds.fds/wer"));
    }
}
