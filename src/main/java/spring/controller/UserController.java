package spring.controller;

import java.util.List;
import java.util.stream.Collectors;
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
public class UserController {
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
        return getDto(userService.get(userId));
    }

    @GetMapping(value = "/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
        .stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto getDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setLogin(user.getLogin());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
