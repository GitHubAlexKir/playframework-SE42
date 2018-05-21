package services;
import models.Agenda;
import models.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserService {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("defaultPersistenceUnit");

    public List<User> findAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        return manager.createNamedQuery("findAllUsers",User.class).getResultList();
    }

    public List<User> find(String name) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        return manager.createNamedQuery("findOneUser",User.class).setParameter("UserName", name)
                .getResultList();
    }

    public void create(User user) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Agenda agenda = new Agenda();
            agenda.setName("testing");
            user.getAgendaList().add(agenda);
            manager.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public void update(User newUser) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            User user = manager.find(User.class, newUser.getId());
            user.setName(newUser.getName());
            user.setAgendaList(newUser.getAgendaList());

            // Update the student
            manager.persist(user);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public void delete(long id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            User user = manager.find(User.class, id);
            manager.remove(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }

            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }
}