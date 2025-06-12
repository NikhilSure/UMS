package com.example.UMS.responseBeautifiers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalResponseModifers implements ResponseBodyAdvice<Object>{
    @Autowired
    HttpServletResponse servletResponse;

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
        responseBody.put("status", servletResponse.getStatus());
        responseBody.put("data", body);
        response.setStatusCode(HttpStatus.OK);
        return responseBody;
    }
}
