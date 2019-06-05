package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.xesam.web.bridge.util.JsonUtils;

public class Listener implements Marshallable {

    private static final String LISTENER_NAME = "name";
    private static final String WEB_CALLBACK_NAME = "name";

    public static Listener parseNative(String content) {
        try {
            Listener listener = new Listener();
            JSONObject jsonObject = new JSONObject(content);
            listener.name = JsonUtils.getString(jsonObject, LISTENER_NAME, "");
            return listener;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String name;

    public Listener() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Listener{" +
                "name=" + name +
                '}';
    }

    @Override
    public String marshalling() {
        JSONObject rs = new JSONObject();
        try {
            rs.put(WEB_CALLBACK_NAME, name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rs.toString();
    }
}
