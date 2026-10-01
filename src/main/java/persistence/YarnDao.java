package persistence;

import entity.Yarn;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class YarnDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get yarn by id
     */
    public Yarn getById(int id) {
        Session session = sessionFactory.openSession();
        Yarn yarn = session.get(Yarn.class, id);
        session.close();
        return yarn;
    }

    /**
     * update yarn
     * @param yarn yarn to be updated
     */
    public void update(Yarn yarn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(yarn);
        transaction.commit();
        session.close();
    }

    /**
     * insert a new user
     * @param yarn Yarn to be inserted
     */
    public int insert(Yarn yarn) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(yarn);
        transaction.commit();
        id = yarn.getId();
        session.close();
        return id;
    }

    /**
     * Delete a yarn
     * @param yarn Yarn to be deleted
     */
    public void delete(Yarn yarn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(yarn);
        transaction.commit();
        session.close();
    }


    /** Return a list of all yarns
     *
     * @return All yarn
     */
    public List<Yarn> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Yarn> query = builder.createQuery(Yarn.class);
        Root<Yarn> root = query.from(Yarn.class);
        List<Yarn> Yarns = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of yarns " + Yarns);
        session.close();

        return Yarns;
    }

    /**
     * Get user by property (exact match)
     */
    public List<Yarn> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for yarn with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Yarn> query = builder.createQuery(Yarn.class);
        Root<Yarn> root = query.from(Yarn.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Yarn> Yarn = session.createSelectionQuery( query ).getResultList();

        session.close();
        return Yarn;
    }

    /**
     * Get yarn by property (like)
     */
    public List<Yarn> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Yarn> query = builder.createQuery(Yarn.class);
        Root<Yarn> root = query.from(Yarn.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Yarn> Yarns = session.createQuery( query ).getResultList();
        session.close();
        return Yarns;
    }

}
