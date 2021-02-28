package com.example.rampagegg.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.rampagegg.R;
import com.example.rampagegg.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    final String REALM_PARAM = "RampageGG";
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSteamURL();
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openSteamURL() {
        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        setContentView(webView);

        String STEAM_URL = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(STEAM_URL);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
                setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    webView.stopLoading();
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();
                    Long steam32 = convertTo64(userId);
                    Log.d(TAG, "My steam ID: " + steam32);
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private Long convertTo64(String userId) {
        return convertTo32(Long.parseLong(userId));
    }

    private Long convertTo32(long steam64) {
        return steam64 - 76561197960265728L;
    }
}