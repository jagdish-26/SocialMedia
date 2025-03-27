package com.Assessment.First.config;

import jakarta.annotation.PostConstruct;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@Configuration
public class WorkflowDeploymentConfig {

    @Autowired
    private RepositoryService repositoryService;

    @Value("${velocious.workflow.processResources}")
    private List<String> workflowProcessResources;

    @PostConstruct
    public void deployWorkflows() {
        for (String workflowProcessResource : workflowProcessResources) {
            try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("workflows/" + workflowProcessResource)) {
                if (resourceStream == null) {
                    throw new RuntimeException("Workflow file not found: " + workflowProcessResource);
                }

                try (ZipInputStream inputStream = new ZipInputStream(resourceStream)) {
                    repositoryService.createDeployment()
                            .name(workflowProcessResource)
                            .addZipInputStream(inputStream)
                            .enableDuplicateFiltering()
                            .deploy();
                }
            } catch (Exception e) {
                throw new RuntimeException("Error deploying workflow: " + workflowProcessResource, e);
            }
        }
    }
}
