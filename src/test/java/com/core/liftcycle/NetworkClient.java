package com.core.liftcycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public void connect() {
        System.out.println("connect = " + url);
    }


    public void call(String message){
        System.out.println("url = " + url + "message" + message);
    }


    public void disconnect(){
        System.out.println("연결종료");
    }

/*
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }*/

    @PostConstruct
    void init() {
        System.out.println("NetworkClient init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    void close() {
        disconnect();
    }


}
