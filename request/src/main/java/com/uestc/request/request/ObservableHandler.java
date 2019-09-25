package com.uestc.request.request;


import com.uestc.request.error.RequestException;
import com.uestc.request.host.BaseUrlBinder;
import com.uestc.request.internal.ClientBuilder;
import com.uestc.request.tools.AScheduler;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import retrofit2.Response;

import java.io.Serializable;

/**
 * Created by PengFeifei on 17-4-20.
 */

public class ObservableHandler {


    public static <S> S getService(Class<S> serviceClass) {
        ClientBuilder.resetBaseUrl(BaseUrlBinder.getClassHost(serviceClass));
        return ClientBuilder.createService(serviceClass);
    }


    public static <T,Data extends Serializable> Observable<Data> getObservable(Observable<T> observable) {
        return observable
                .observeOn(AScheduler.uiScheduler)
                .subscribeOn(AScheduler.backScheduler)
                .filter(filterHttpResponse())
                .map(map2RootData());
    }

    private static  <T,Data extends Serializable> Function<T, Data> map2RootData() {
        return new Function<T, Data>() {
            @Override
            public Data apply(T t) {
                if (t != null && t instanceof Response) {
                    Response<T> response = (Response<T>) t;
                    return (Data) response.body();
                }
                return null;
            }
        };
    }


    private static  <T> Predicate<T> filterHttpResponse() {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) throws Exception {
                if (t != null && t instanceof Response) {
                    Response<T> response = (Response<T>) t;
                    if (response.code() != 200) {
                        int code = response.code();
                        throw new RequestException(response.raw().request().url(), code, "response code--> " + code + " != 200");
                    }
                    return response.isSuccessful();
                }
                return true;
            }
        };
    }


}
