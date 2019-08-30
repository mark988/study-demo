package com.example.server.utils;

import com.example.server.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/***
 * @author : 马晓光
 * @date   : 2019/8/30
 * @description :
 **/
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
