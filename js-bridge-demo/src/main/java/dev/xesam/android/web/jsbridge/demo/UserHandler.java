package dev.xesam.android.web.jsbridge.demo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import io.github.xesam.web.bridge._native.NativeHandler;
import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.BridgeError;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Response;

/**
 * Created by xesamguo@gmail.com on 16-4-8.
 */
public class UserHandler implements NativeHandler {

    private Context mContext;

    public UserHandler(Context mContext) {
        this.mContext = mContext;
    }

    private User getUser() {
        return new User();
    }

    @Override
    public String getName() {
        return "getNativeUser";
    }

    @Override
    public void handle(Param param, WebCallback callback) {
        User user = getUser();
        Map<String, String> map = new Gson().fromJson(param.getContent().toString(), new TypeToken<HashMap<String, Object>>() {
        }.getType());
        String prefix = map.get("name_prefix");
        Tip.showTip(mContext, "user.getName():" + prefix + "/" + user.getName());
        if ("standard_error".equals(prefix)) {
            callback.call(new Response(new BridgeError("10001", "get user failed"), null));
        } else {
            String userMarshalling = new Gson().toJson(user);
            callback.call(new Response(null, null));
        }
    }
}
