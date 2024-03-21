package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query =
                entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByID(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserByID(Long id) {
        entityManager
                .createQuery("delete from User u where u.id =: id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.flush();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }
}
