package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.xesam.web.bridge.util.JsonUtils;

public class BridgeError implements Deliverable {

    public static BridgeError from(JSONObject jsonObject) {
        BridgeError error = new BridgeError();
        try {
            error.status = JsonUtils.getString(jsonObject, Protocol.Native.ERROR_STATUS, "");
            error.message = JsonUtils.getString(jsonObject, Protocol.Native.ERROR_MESSAGE, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }

    private String status;
    private String message;

    private BridgeError() {
    }

    public BridgeError(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        try {
            jo.put(Protocol.Web.ERROR_STATUS, status);
            jo.put(Protocol.Web.ERROR_MESSAGE, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
