package org.example;


import java.io.IOException;

// 약속 : GET /calculate 요청이 오면 계산을 수행해서 반환한다.
// /calculate&operand1=11&operator=*&operand2=55
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8083).start();

    }
}
