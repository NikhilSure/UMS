package com.example.UMS.responseBeautifiers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseModifers implements ResponseBodyAdvice<Object>{
    @Override
    public boolean supports(MethodParameter retunType, Class <? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType contentType,
            Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Map && ((Map<?,?>) body).containsKey("status")) {
            return body;
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("time", LocalDateTime.now());
        responseBody.put("status", HttpStatus.ACCEPTED);
        responseBody.put("data", body);
        response.setStatusCode(HttpStatus.OK);
        return responseBody;
    }
}
