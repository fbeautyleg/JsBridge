package io.github.xesam.web.bridge.core;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.xesam.web.bridge.util.JsonUtils;

public class Action implements Marshallable {

    public static Action parse(String content) {
        Action action = null;
        try {
            JSONObject jsonObject = new JSONObject(content);
            action = new Action(JsonUtils.getInt(jsonObject, Protocol.Native.ACTION_TYPE, Protocol.Native.TYPE_CALL));
            action.version = JsonUtils.getString(jsonObject, Protocol.Native.NATIVE_BRIDGE_VERSION, null);
            action.name = JsonUtils.getString(jsonObject, Protocol.Native.ACTION_NAME, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return action;
    }

    public static Action from(Listener listener) {
        Action action = new Action(Protocol.Web.TYPE_CALLBACK);
        action.version = Protocol.Native.VERSION;
        action.name = listener.getName();
        return action;
    }

    private final int type;
    private String version;
    private String name;

    private Action(int type) {
        this.type = type;
    }

    public Action(int type, String name) {
        this(type);
        this.name = name;
        this.version = Protocol.Native.VERSION;
    }

    public int getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    @Override
    public String marshalling() {
        JSONObject rs = new JSONObject();
        try {
            rs.put(Protocol.Web.ACTION_TYPE, type);
            rs.put(Protocol.Web.ACTION_NAME, name);
            rs.put(Protocol.Web.WEB_BRIDGE_VERSION, version);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rs.toString();
    }

    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
