package com.tugalsan.api.storage.cookie.server;

import com.tugalsan.api.cast.client.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.stream.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.nio.charset.*;
import java.util.*;
import javax.servlet.http.*;

public class TS_StorageCookieUtils {

    public static List<String> names(HttpServletRequest req) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return TGS_ListUtils.of();
        }
        return TGS_StreamUtils.toLst(Arrays.stream(cookies).map(c -> c.getName()));
    }

    public static TGS_UnionExcuse<Integer> valInt(HttpServletRequest req, String name) {
        var u = valStr(req, name);
        if (u.isExcuse()) {
            return u.toExcuse();
        }
        return TGS_CastUtils.toInteger(u.value());
    }

    public static TGS_UnionExcuse<String> valStr(HttpServletRequest req, String name) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return TGS_UnionExcuse.ofExcuse(TS_StorageCookieUtils.class.getSimpleName(), "valStr", "cookies == null");
        }
        var found = Arrays.stream(cookies).filter(c -> Objects.equals(c.getName(), name)).findAny().orElse(null);
        if (found == null) {
            return TGS_UnionExcuse.ofExcuse(TS_StorageCookieUtils.class.getSimpleName(), "valStr", "not found");
        }
        return TGS_UnionExcuse.of(java.net.URLDecoder.decode(found.getValue(), StandardCharsets.UTF_8));
    }
}
