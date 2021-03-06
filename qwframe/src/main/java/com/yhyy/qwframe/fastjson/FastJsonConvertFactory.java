package com.yhyy.qwframe.fastjson;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by IceWolf on 2019/9/19.
 */
public class FastJsonConvertFactory<T> extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("utf-8");

    @Override
    public Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new Converter<T, RequestBody>() {
            @Override
            public RequestBody convert(T value) throws IOException {
                return RequestBody.create(JSON.toJSONString(value).getBytes(UTF_8), MEDIA_TYPE);
            }
        };
    }

    @Override
    public Converter<ResponseBody, T> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        return new Converter<ResponseBody, T>() {
            @Override
            public T convert(ResponseBody value) throws IOException {
                return JSON.parseObject(value.string(), type);
            }
        };
    }
}