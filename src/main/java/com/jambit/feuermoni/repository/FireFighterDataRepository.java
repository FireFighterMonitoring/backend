package com.jambit.feuermoni.repository;


import com.jambit.feuermoni.model.FireFighterData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = FireFighterData.class, idClass = Long.class)
public interface FireFighterDataRepository extends FireFighterDataRepositoryCustom {

    FireFighterData save(FireFighterData entity);

    @Query(
            nativeQuery = true,
            value = "select * from fire_fighter_data where id in (" +
                    "    select max(id) from fire_fighter_data where read = 'false' group by ff_id" +
                    ")")
    Iterable<FireFighterData> findLatestData();
}
