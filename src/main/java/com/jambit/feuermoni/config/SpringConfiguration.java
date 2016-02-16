package com.jambit.feuermoni.config;


import com.jambit.feuermoni.model.FireFighterData;
import com.jambit.feuermoni.repository.FireFighterDataRepository;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class, FireFighterData.class })
@EnableJpaRepositories(basePackageClasses = FireFighterDataRepository.class, enableDefaultTransactions = false)
public class SpringConfiguration {

}
