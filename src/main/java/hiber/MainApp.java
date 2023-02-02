package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car("Model1", 123);
        car1.setUser(user1);
        user1.setCar(car1);
        userService.add(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("Model2", 321);
        car2.setUser(user2);
        user2.setCar(car2);
        userService.add(user2);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println(userService.getCarOwner("Model2", 321).getFirstName());

        context.close();
    }
}
