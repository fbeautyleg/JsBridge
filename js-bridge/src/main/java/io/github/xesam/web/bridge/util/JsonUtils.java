package io.github.xesam.web.bridge.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static String getString(JSONObject jsonObject, String key, String defaultValue) throws JSONException {
        if (jsonObject.has(key)) {
            return jsonObject.getString(key);
        }
        return defaultValue;
    }

    public static long getLong(JSONObject jsonObject, String key, long defaultValue) throws JSONException {
        if (jsonObject.has(key)) {
            return jsonObject.getLong(key);
        }
        return defaultValue;
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) throws JSONException {
        if (jsonObject.has(key)) {
            return jsonObject.getInt(key);
        }
        return defaultValue;
    }

    public static JSONObject getObject(JSONObject jsonObject, String key, JSONObject defaultValue) throws JSONException {
        if (jsonObject.has(key)) {
            return jsonObject.getJSONObject(key);
        }
        return defaultValue;
    }
}
