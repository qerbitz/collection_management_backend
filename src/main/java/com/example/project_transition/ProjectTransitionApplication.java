package com.example.project_transition;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories
@EnableTransactionManagement
public class ProjectTransitionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
       // SpringApplication.run(ProjectTransitionApplication.class, args);
        SpringApplicationBuilder app = new SpringApplicationBuilder(ProjectTransitionApplication.class);
        app.run();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectTransitionApplication.class);
    }
}
