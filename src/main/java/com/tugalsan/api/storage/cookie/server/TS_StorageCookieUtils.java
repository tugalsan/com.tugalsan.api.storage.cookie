package com.tugalsan.api.storage.cookie.server;

import com.tugalsan.api.cast.client.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.stream.client.*;
import java.nio.charset.*;
import java.util.*;
import javax.servlet.http.*;

public class TS_StorageCookieUtils {

    public static List<String> names(HttpServletRequest req) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return TGS_ListUtils.of();
        }
        return TGS_StreamUtils.toList(Arrays.stream(cookies).map(c -> c.getName()));
    }

    public static Integer valInt(HttpServletRequest req, String name) {
        return TGS_CastUtils.toInteger(valStr(req, name));
    }

    public static String valStr(HttpServletRequest req, String name) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        var r = Arrays.stream(cookies).filter(c -> Objects.equals(c.getName(), name)).findAny().orElse(null);
        return r == null ? null : java.net.URLDecoder.decode(r.getValue(), StandardCharsets.UTF_8);
    }
}
