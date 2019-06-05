package dev.xesam.android.web.jsbridge.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge._native.NativeCallback;
import io.github.xesam.web.bridge._web.WebCall;
import io.github.xesam.web.bridge.core.BridgeError;
import io.github.xesam.web.bridge.core.Data;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    public WebView vWebView;

    @BindView(R.id.bg_color)
    public EditText vBgColor;

    Bridge jsBridge;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        vWebView = findViewById(R.id.webview);
        jsBridge = new Bridge(vWebView);

        vWebView.getSettings().setJavaScriptEnabled(true);
        vWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished", url);
//                jsBridge.monitor(url);
            }
        });
        jsBridge.registerHandler(new UserHandler(this));
        vWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jsBridge.destroy();
    }

    @Override
    public void onBackPressed() {
        if (vWebView.canGoBack()) {
            vWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.get_web_title)
    public void getWebTitle() {
        WebCall webCall = new WebCall("getWebTitle");
        jsBridge.invoke(webCall, null, new NativeCallback() {
            @Override
            public void call(Response response) {
                BridgeError err = response.getError();
                if (err == null) {
                    Tip.showTip(getApplicationContext(), "getWebTitle error");
                } else {
                    Data data = response.getData();
                    JSONObject jo = data.toJSONObject();
                    try {
                        Tip.showTip(getApplicationContext(), "getWebTitle success:" + jo.get("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @OnClick(R.id.set_web_bg_red)
    public void setWebBgRed() {
        WebCall webCall = new WebCall("setWebBg");
        JSONObject jo = new JSONObject();
        try {
            String color = vBgColor.getText() == null ? "red" : vBgColor.getText().toString();
            jo.put("backgroundColor", color);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Param param = new Param(jo);
        jsBridge.invoke(webCall, param, null);
    }
}
