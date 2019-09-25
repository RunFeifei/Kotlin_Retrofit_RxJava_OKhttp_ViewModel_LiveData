package com.uestc.request.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ExtendInterceptor implements Interceptor {
    private List<Interceptor> interceptors = new ArrayList<>(2);

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (interceptors != null && interceptors.size() > 0) {
            List<Interceptor> list = new ArrayList<>();
            list.addAll(interceptors);
            Response response = null;
            Chain ch = chain;
            for (Interceptor i : interceptors) {
                ch = new MyChain(ch.request(), ch.connection(), ch, response);
                response = i.intercept(ch);
            }
            return response;
        }
        return chain.proceed(chain.request());
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    private static class MyChain implements Chain {

        private Request request;
        private Connection connection;
        private Chain chain;
        private Response response;

        MyChain(Request request, Connection connection, Chain chain, Response response) {
            this.request = request;
            this.connection = connection;
            this.chain = chain;
            this.response = response;
        }

        @Override
        public Request request() {
            return request;
        }

        @Override
        public Response proceed(Request request) throws IOException {
            if (response != null) {
                return response;
            }
            return chain.proceed(request);
        }

        @Override
        public Connection connection() {
            return connection;
        }


        @Override
        public Call call() {
            return chain.call();
        }

        @Override
        public int connectTimeoutMillis() {
            return chain.connectTimeoutMillis();
        }

        @Override
        public Chain withConnectTimeout(int timeout, TimeUnit unit) {
            return chain;
        }

        @Override
        public int readTimeoutMillis() {
            return chain.readTimeoutMillis();
        }

        @Override
        public Chain withReadTimeout(int timeout, TimeUnit unit) {
            return chain;
        }

        @Override
        public int writeTimeoutMillis() {
            return chain.writeTimeoutMillis();
        }

        @Override
        public Chain withWriteTimeout(int timeout, TimeUnit unit) {
            return chain;
        }
    }
}
