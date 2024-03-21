package crud.service;

import crud.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByID(Long id);

    void updateUser(User user);

    void deleteUserByID(Long id);

    void createUser(User user);
}
