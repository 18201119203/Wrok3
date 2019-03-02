package com.example.lib_netwrok.network;

import com.example.lib_netwrok.utils.SpUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 头部拦截器
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();//原始请求对象
        Request nrequest = originalRequest.newBuilder()
                .addHeader("userId","132")
                .addHeader("sessionId","1551332768505132")
                .build();

        Response response = chain.proceed(nrequest);

        return response;
    }
}
