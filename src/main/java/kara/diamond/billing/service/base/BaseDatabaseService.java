package kara.diamond.billing.service.base;

import kara.diamond.billing.service.model.request.OrderDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public abstract class BaseDatabaseService extends BaseService {
	
	@Autowired EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


}