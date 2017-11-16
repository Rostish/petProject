package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
        System.out.println(adminRestController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));
        System.out.println();
//        UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
        UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);
        userMealRestController.getAll();

        List<UserMealWithExceed> filteredMealWithExceeded =
                userMealRestController.getBetween(
                        LocalDate.of(2015, Month.MAY, 30), LocalTime.of(0,10),
                        LocalDate.of(2015, Month.MAY, 31), LocalTime.of(20,10));
        filteredMealWithExceeded.forEach(System.out::println);
    }
}
