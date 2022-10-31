package com.example.task_1.config;

import com.example.task_1.controllers.UserController;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.entities.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {


    //// НЕ РАБОТАЕТ
    @Override
    public void configureRepositoryRestConfiguration(
           RepositoryRestConfiguration config, CorsRegistry cors){
            config.exposeIdsFor(UserController.class);
    }
}
