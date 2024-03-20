package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    public UserDaoImp(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        TypedQuery<User> query =
                entityManagerFactory.createEntityManager().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public User getUserByID(Long id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(user);
        entityTransaction.commit();
    }

    @Override
    @Transactional
    public void deleteUserByID(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.joinTransaction();
        entityManager
                .createQuery("delete from User u where u.id =: id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.flush();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        entityManagerFactory.createEntityManager().persist(user);
    }
}
