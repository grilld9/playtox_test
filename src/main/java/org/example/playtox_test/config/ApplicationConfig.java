package org.example.playtox_test.config;

import lombok.RequiredArgsConstructor;
import org.example.playtox_test.entity.Account;
import org.example.playtox_test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AccountRepository accountRepository;

    @Value("${bank.accounts-count}")
    private Integer accountsCount;


    @Bean
    public CommandLineRunner accountInitialization() {
        return args -> {
            for (int i = 0; i < accountsCount; i++) {
                accountRepository.save(new Account(null, 10000L));
            }
        };
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
