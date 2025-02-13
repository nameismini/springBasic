package com.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find bean1");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("find bean2");

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        bean1.close();
        bean2.close();

    }


    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        void close(){
            System.out.println("PrototypeBean.close");
        }
    }


}
