package dev.xesam.android.web.jsbridge.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xesam.web.bridge.Bridge;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    public WebView vWebView;

    Bridge jsBridge;

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
//        jsBridge.eval("window.jsFn1()");
    }

    @OnClick(R.id.set_web_bg_red)
    public void setWebBgRed() {
//        jsBridge.invoke("jsFn1");
    }
}
