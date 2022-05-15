package kara.diamond.billing.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class BaseDatabaseService extends BaseService {
	
	@Autowired EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}