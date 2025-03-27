package com.Assessment.First.config;

import lombok.extern.log4j.Log4j2;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Log4j2
public class WorkflowAppEngineConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Autowired
    @Qualifier("flowableDataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("flowableTransactionManager")
    private PlatformTransactionManager transactionManager;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        log.debug("Configuring datasource for Flowable Process Engine");
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration.setTransactionManager(transactionManager);
        springProcessEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    }
}
