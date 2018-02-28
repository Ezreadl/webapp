package com.qm.core.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public class BaseDaoFactory<S, ID extends Serializable> extends JpaRepositoryFactory {

    public BaseDaoFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "hiding" })
    @Override
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information,EntityManager entityManager) {
    	
    	BaseDaoImpl baseDao= new BaseDaoImpl(information.getDomainType(), entityManager);
    	baseDao.setInformation(information);
    	return baseDao;
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        // TODO Auto-generated method stub
        return IBaseDao.class;
    }

}