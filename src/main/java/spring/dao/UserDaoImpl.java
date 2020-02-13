package spring.dao;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void add(User user) {
        try {
            Long id = (Long) sessionFactory.openSession().save(user);
            user.setId(id);
        } catch (Exception e) {
            LOGGER.error("Unable to add new user");
        }
    }

    public List<User> listUsers() {
        try(Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
            query.from(User.class);
            return session.createQuery(query).getResultList();
        }
    }
}
