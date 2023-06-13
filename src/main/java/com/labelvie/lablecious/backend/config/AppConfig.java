package com.labelvie.lablecious.backend.config;

import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.MenuPlates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Menu menu() {
        return new Menu();
    }
    @Bean
    public MenuPlates menuPlate() {
        return new MenuPlates();
    }


}

