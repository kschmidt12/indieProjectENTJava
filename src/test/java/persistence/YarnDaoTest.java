package persistence;

import entity.User;
import entity.Yarn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YarnDaoTest {

    YarnDao yarnDao;
    UserDao userDao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean.sql");
        yarnDao = new YarnDao();
        userDao = new UserDao();
    }

    @Test
    void getById() {
        Yarn retrievedYarn = yarnDao.getById(1);
        assertEquals("Lions Brand", retrievedYarn.getBrandName());
    }

    @Test
    void update() {
        Yarn retrievedYarn = yarnDao.getById(1);
        retrievedYarn.setColor("green");
        yarnDao.update(retrievedYarn);
        Yarn changedYarn = yarnDao.getById(1);
        assertEquals("green", changedYarn.getColor());
    }

    @Test
    void insert() {

        User user = userDao.getById(1);
        Yarn yarn = new Yarn();
        yarn.setBrandName("I Love This Yarn");
        yarn.setColor("pink");
        yarn.setYarnAmount("4");
        yarn.setYarnSize("3");
        yarn.setHookSize("5 to 6");
        yarn.setBoughtFrom("Hobby Lobby");
        yarn.setUser(user);
        yarnDao.insert(yarn);

        Yarn newYarn = yarnDao.getById(yarn.getId());

        assertEquals("I Love This Yarn", newYarn.getBrandName());

        assertEquals(user.getId(), newYarn.getUser().getId());
    }

    @Test
    void delete() {
        Yarn yarn = new Yarn();
        Yarn retrievedYarn = yarnDao.getById(1);

        yarnDao.delete(retrievedYarn);
        Yarn deletedYarn = yarnDao.getById(yarn.getId());
        assertNull(deletedYarn);

        assertNotNull(userDao.getById(1));
    }

    @Test
    void getAll() {
        List<Yarn> yarns = yarnDao.getAll();
        assertEquals(2, yarns.size());
    }

    @Test
    void getByPropertyEqual() {
        List<Yarn> yarn = yarnDao.getByPropertyEqual("color", "purple");
        assertEquals(1, yarn.size());
        assertEquals(1, yarn.get(0).getId());
    }

    @Test
    void getByPropertyLike() {
        List<Yarn> yarn = yarnDao.getByPropertyLike("brandName", "L");
        assertEquals(1, yarn.size());
        assertEquals(1, yarn.get(0).getId());
    }
}