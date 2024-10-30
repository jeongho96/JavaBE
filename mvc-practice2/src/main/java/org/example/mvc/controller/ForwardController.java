package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 바로 그 페이지로 이동하는 컨트롤러
public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return forwardUrl;
    }
}