package com.bawei.zuoyeohe.util;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.zuoyeohe.R;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetUtil {


    private final Handler handler;
    private final OkHttpClient okHttpClient;

    public NetUtil() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    private static NetUtil netUtil;

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }
    public void getJsonGet(String httpurl,MyCallback myCallback){
        Request request = new Request.Builder()
                .get()
                .url(httpurl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null&&response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                          myCallback.onGetJson(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onError(new Exception("哈哈"));
                        }
                    });
                }
            }
        });
    }
    public interface MyCallback{
        void onGetJson(String json);
        void onError(Throwable throwable);
    }

    public void getPhono(String imageurl, ImageView imageView){
        Glide.with(imageView).load(imageurl).into(imageView);
    }
    public void getJsonPost(String httpurl, Map<String,String> map, MyCallback myCallback){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()){
            String s = map.get(key);
            builder.add(s,key);
        }
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .post(build)
                .url(httpurl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null&&response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onGetJson(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onError(new Exception("哈哈"));
                        }
                    });
                }
            }
        });
    }
}
