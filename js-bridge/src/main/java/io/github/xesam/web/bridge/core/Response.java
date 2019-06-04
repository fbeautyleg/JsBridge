package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

public class Response implements Marshallable {

    public static Response parse(String content) {
        Response response = new Response();
        try {
            JSONObject jsonObject = new JSONObject(content);
            if (jsonObject.has(Protocol.Native.RESPONSE_ERROR)) {
                response.error = BridgeError.from(jsonObject);
            } else if (jsonObject.has(Protocol.Native.RESPONSE_DATA)) {
                response.data = Data.from(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private BridgeError error;
    private Data data;

    private Response() {
    }

    public Response(BridgeError err, Data data) {
        this.error = err;
        this.data = data;
    }

    public BridgeError getError() {
        return error;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String marshalling() {
        JSONObject jo = new JSONObject();
        try {
            if (error != null) {
                jo.put(Protocol.Web.RESPONSE_ERROR, error.toJSONObject());
            } else if (data != null) {
                jo.put(Protocol.Web.RESPONSE_DATA, data.toJSONObject());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }
}
