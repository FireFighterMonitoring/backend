package com.jambit.feuermoni.service;


import java.time.Instant;

import com.jambit.feuermoni.model.FireFighterData;
import com.jambit.feuermoni.repository.FireFighterDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class FireFighterDataService {

    private final FireFighterDataRepository repository;

    @Autowired
    public FireFighterDataService(FireFighterDataRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public FireFighterData save(FireFighterData data) {
        log.info("Saving data {}", data);
        data.setTimestamp(Instant.now());

        return repository.save(data);
    }

    @Transactional
    public Iterable<FireFighterData> loadLatest() {
        log.info("Loading latest data");
        Iterable<FireFighterData> data = repository.findLatestData();

        log.info("Marking all data as read");
        repository.markAllRead();

        return data;
    }
}
