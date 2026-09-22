package entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a entity.user.
 *
 * @author Katie Schmidt
 */
@Entity
@Table(name = "entity.user")

public class user {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;


    /**
     * Instantiates a new User.
     */
    public user() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name     the  name
     * @param email    the email
     * @param userName the username
     * @param id       the id
     * @param password the password
     */
    public user(int id, String name, String email, String userName, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the  name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String email() {
        return email;
    }

    /**
     * Sets email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets entity.user name.
     *
     * @return the entity.user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets entity.user name.
     *
     * @param userName the entity.user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}