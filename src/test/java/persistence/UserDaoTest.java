package persistence;

import entity.User;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;

    @Test
    void getById() {
        userDao = new UserDao();
        User retrievedUser = userDao.getById(1);
        assertEquals("Katie Schmidt", retrievedUser.getName());
    }

    @Test
    void update() {
        userDao = new UserDao();
        User retrievedUser = userDao.getById(1);
        retrievedUser.setUserName("changedUsername");
        userDao.update(retrievedUser);
        User changedUser = userDao.getById(1);
        assertEquals("changedUsername", changedUser.getUserName());
    }

    @Test
    void insert() {
        userDao = new UserDao();

        User user = new User();
        user.setName("Harper Schmidt");
        user.setEmail("harper@gmail.com");
        user.setUserName("HarperSchmidt");
        user.setPassword("ILoveChickenNuggets");
        userDao.insert(user);

        User newUser = userDao.getById(user.getId());

        assertEquals("Harper Schmidt", newUser.getName());
    }

    @Test
    void delete() {
        userDao = new UserDao();

        User user = new User();
        user.setName("Deleted User");
        user.setEmail("deleted@gmail.com");
        user.setUserName("deleted");
        user.setPassword("ImGoingToBeDeleted");
        userDao.insert(user);

        User retrievedUser = userDao.getById(user.getId());
        userDao.delete(retrievedUser);
        User deletedUser = userDao.getById(user.getId());
        assertNull(deletedUser);
    }

    @Test
    void getAll() {
        userDao = new UserDao();
        List<User> users = userDao.getAll();
        assertEquals(2, users.size());

    }

    @Test
    void getByPropertyEqual() {
        userDao = new UserDao();
        List<User> user = userDao.getByPropertyEqual("name", "Katie Schmidt");
        assertEquals(1, user.size());
        assertEquals(1, user.get(0).getId());
    }

    @Test
    void getByPropertyLike() {
        userDao = new UserDao();
        List<User> user = userDao.getByPropertyLike("name", "K");
        assertEquals(1, user.size());
        assertEquals(1, user.get(0).getId());
    }


}