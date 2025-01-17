package com.core.singleton;

public class SingletonService {


    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        // 외부에서 생성자 호출 막기위함
    }

    public void logic() {
        System.out.println("싱글톤 테스트");
    }

}
