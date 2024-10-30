package org.example;



import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아 출력한다.
 */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);


    // 해당 패키지 밑에 Controller라는 어노테이션이 붙은 클래스를 찾는 것.
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans [{}]", beans);
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        });

        return beans;
    }

    @Test
    void showClass(){
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields : [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors : [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods : [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    // 힙영역에 로드되어 있는 클래스 객체를 가져오는 방법
    @Test
    void load() throws ClassNotFoundException {
        // 1번
        Class<User> clazz = User.class;

        // 2번
        User user = new User("serverwizard", "홍종완");
        Class<? extends User> clazz2 = user.getClass();

        // 3번
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz : [{}]", clazz);
        logger.debug("clazz2 : [{}]", clazz2);
        logger.debug("clazz3 : [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz == clazz3).isTrue();

    }
}
