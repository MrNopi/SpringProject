package spring;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppConfig;
import spring.model.User;
import spring.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setLogin("admin");
        user.setPassword("12345");
        userService.add(user);
        List<User> users = userService.listUsers();
        for (User u : users) {
            System.out.println(u.getId());
            System.out.println(u.getLogin());
            System.out.println(u.getPassword());
            System.out.println("-------");
        }
    }
}
