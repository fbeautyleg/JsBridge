package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.xesam.web.bridge.util.JsonUtils;

public class Data implements Deliverable {

    public static Data from(JSONObject jsonObject) {
        try {
            Data data = new Data();
            data.data = JsonUtils.getObject(jsonObject, Protocol.Native.RESPONSE_DATA, null);
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
