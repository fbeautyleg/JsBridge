package dev.xesam.android.web.jsbridge.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.github.xesam.web.bridge._native.NativeHandler;
import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.BridgeError;
import io.github.xesam.web.bridge.core.Data;
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
    public void handle(Param param, final WebCallback callback) {
        final User user = getUser();
        Map<String, String> map = new Gson().fromJson(param.getContent().toString(), new TypeToken<HashMap<String, Object>>() {
        }.getType());
        final String userId = map.get("userId");

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Tip.showTip(mContext, "user.getName():" + userId + ":" + user.getName());
                if ("no_id".equals(userId)) {
                    callback.call(new Response(new BridgeError("10001", "get user failed"), null));
                } else {
                    Data data = null;
                    try {
                        data = new Data(new JSONObject(new Gson().toJson(user)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callback.call(new Response(null, data));
                }
            }
        });
    }
}
