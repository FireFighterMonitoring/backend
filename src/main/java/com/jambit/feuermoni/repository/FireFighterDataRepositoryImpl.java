package com.jambit.feuermoni.repository;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FireFighterDataRepositoryImpl implements FireFighterDataRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public FireFighterDataRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void markAllRead() {
        entityManager.createQuery("update FireFighterData set read = true").executeUpdate();
    }
}
