package com.jambit.feuermoni.repository;


import com.jambit.feuermoni.model.FireFighterData;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = FireFighterData.class, idClass = Long.class)
public interface FireFighterDataRepository {

    FireFighterData save(FireFighterData entity);

    Iterable<FireFighterData> findAll();
}
