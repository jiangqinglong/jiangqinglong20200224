package com.bw.jiangqinglong20200224.NetUtil;

import android.util.Log;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.security.auth.callback.Callback;

/**
 * 作者：姜庆龙
 * 时间：2020/2/24
 * 功能：
 */
public class NetUtil {
    private static final String TAG = "NetUtil";
    public static NetUtil netUtil = new NetUtil();

    private NetUtil() {
    }

    public static NetUtil getNetUtil() {
        return netUtil;
    }

    public interface CallBack {
        void success(String json);

        void falied(String msg);
    }

    public void getJson(final String path, final Callback callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(path);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    StringBuilder builder = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {

                        builder.append(new String(bytes, 0, len));

                    }
                    String json = builder.toString();
                    inputStream.close();
                    Log.d(TAG, "run: ----" + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
