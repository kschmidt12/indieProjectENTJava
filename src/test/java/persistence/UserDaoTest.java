package persistence;

import entity.User;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void getByPropertyEqual() {
    }

    @Test
    void getByPropertyLike() {
    }
}