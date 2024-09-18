package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserDao {

    @Autowired
    private EntityManager entityManager;


    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public User getByIdUser(Long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
