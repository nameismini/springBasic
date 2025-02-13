package com.core.beanfind;

import com.core.discount.DiscountPolicy;
import com.core.discount.FixDiscountPolicy;
import com.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByparentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 명으로 호출")
    void findBeanByparentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("특정 하위타입으로 조회")
    void findBeanBySubType() {
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 혹은 Object 모두 불러오기")
    void findBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println(s);
        }

    }


    @Test
    @DisplayName("특정 빈이 어디에 등록되어있는지 찾기위함")
    void findBeanLocation() {
        String beanName = "fixDiscountPolicy";

        /*if (ac.containsBean(beanName)) {
            BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) ac).getBeanDefinition(beanName);

            System.out.println("🔍 Bean Name: " + beanName);
            System.out.println("    - Bean Class: " + ac.getBean(beanName).getClass().getName());
            System.out.println("    - Bean Definition Source: " + beanDefinition.getResourceDescription());
            System.out.println("    - Bean Definition Role: " +
                    (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION ? "Application Bean" : "Spring Internal Bean"));
        } else {
            System.out.println("❌ '" + beanName + "' 빈을 찾을 수 없습니다.");
        }*/


        for (String beanNames : ac.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanNames);
            System.out.println("🔍 Bean Name: " + beanNames);
            System.out.println("    - Bean Class: " + ac.getBean(beanNames).getClass().getName());
            System.out.println("    - Bean Definition Source: " + beanDefinition.getResourceDescription());
            System.out.println();
        }
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
//            return null;
        }
    }


}
