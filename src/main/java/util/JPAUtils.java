package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotel_alura");
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
