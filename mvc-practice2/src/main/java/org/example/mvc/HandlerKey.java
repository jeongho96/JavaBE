package org.example.mvc;

import org.example.mvc.controller.RequestMethod;

import java.util.Objects;

// HTTP 메소드가 달라도 URI가 같을 수 있는데, 그걸 Map 객체에 넣어줄수가 없음.(Key는 유니크)
// 그걸 처리하기 위한 Key를 만들어줌.
public class HandlerKey {
    private String url;
    private RequestMethod requestMethod;

    public HandlerKey(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return Objects.equals(url, that.url) && requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, requestMethod);
    }

    @Override
    public String toString() {
        return "HandlerKey{" +
                "url='" + url + '\'' +
                ", requestMethod=" + requestMethod +
                '}';
    }
}