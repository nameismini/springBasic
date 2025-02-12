package com.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {


    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeans.class, PrototypeBean.class);

        ClientBeans clientBeans1 = ac.getBean(ClientBeans.class);

        int count1 = clientBeans1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBeans clientBeans2 = ac.getBean(ClientBeans.class);
        int count2 = clientBeans2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    //    @Component
    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBeans {
/*        private final PrototypeBean prototypeBean;  //생성 시점에 주입

        @Autowired
        ClientBeans(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/


        private final ObjectProvider<PrototypeBean> prototypeBeansProvider; //lombok
//        private final Provider<PrototypeBean> prototypeBeansProvider; //lombok, javax inject ... 빈을 못가져옴

        /*@Autowired
        public ClientBeans(ObjectProvider<PrototypeBean> prototypeBeansProvider) {
            this.prototypeBeansProvider = prototypeBeansProvider;
        }*/

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeansProvider.getObject();
//            PrototypeBean prototypeBean = prototypeBeansProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void addCount() {
            this.count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);

        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory " + this);
        }
    }

}
