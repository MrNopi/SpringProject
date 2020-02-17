package spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dto.UserResponseDto;
import spring.model.User;
import spring.service.UserService;

@RestController()
@RequestMapping("/user")
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/inject")
    public void inject() {
        User user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        userService.add(user);

        User user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("12");
        userService.add(user2);

        User user3 = new User();
        user3.setLogin("user3");
        user3.setPassword("123");
        userService.add(user3);

        User user4 = new User();
        user4.setLogin("user4");
        user4.setPassword("1234");
        userService.add(user4);
    }

    @GetMapping(value = "/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userService.get(userId);
        userResponseDto.setLogin(user.getLogin());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = "/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> users = userService.listUsers();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setLogin(user.getLogin());
            userResponseDto.setPassword(user.getPassword());
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }
}
