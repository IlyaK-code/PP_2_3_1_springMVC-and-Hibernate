package web.dao;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Transactional
    public User getByIdUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(Long id, User updateUser) {
        User userToBeUpdate = getByIdUser(id);
        userToBeUpdate.setName(updateUser.getName());
        userToBeUpdate.setEmail(updateUser.getEmail());
    }

    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
