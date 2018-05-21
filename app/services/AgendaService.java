package services;

import models.Agenda;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class AgendaService {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("defaultPersistenceUnit");

    public Agenda find(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneAgenda",Agenda.class).setParameter("id", id).getSingleResult();
    }
}