package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

public class Param implements Marshallable {
    public static Param parseNative(String content) {
        Param param = new Param();
        try {
            param.content = new JSONObject(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return param;
    }

    private JSONObject content;

    private Param() {
    }

    public Param(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }

    @Override
    public String marshalling() {
        return content.toString();
    }
}
