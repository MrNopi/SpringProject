package spring.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import spring.config.AppConfig;
import spring.dao.UserDao;
import spring.dao.UserDaoImpl;
import spring.model.User;
import spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //UserDao userDao = new UserDaoImpl();
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
