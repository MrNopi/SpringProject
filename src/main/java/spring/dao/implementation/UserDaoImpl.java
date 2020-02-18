package spring.dao.implementation;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.dao.UserDao;
import spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(User user) {
        try {
            Long id = (Long) sessionFactory.openSession().save(user);
            user.setId(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to add new user");
        }
    }

    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
            query.from(User.class);
            return session.createQuery(query).getResultList();
        }
    }

    public User get(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get user with id " + userId);
        }
    }
}
