package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.xesam.web.bridge.util.JsonUtils;

public class Data implements Deliverable {

    public static Data from(JSONObject jsonObject) {
        Data item = new Data();
        try {
            item.data = JsonUtils.getObject(jsonObject, "data", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    private JSONObject data;

    protected Data() {
    }

    public Data(JSONObject jsonObject) {
        this.data = jsonObject;
    }

    @Override
    public JSONObject toJSONObject() {
        return this.data;
    }
}
