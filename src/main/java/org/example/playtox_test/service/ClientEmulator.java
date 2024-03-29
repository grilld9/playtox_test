package org.example.playtox_test.service;


import lombok.NoArgsConstructor;
import org.example.playtox_test.entity.Account;
import org.example.playtox_test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@NoArgsConstructor
public class ClientEmulator {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${app.thread-count}")
    private int threadCount;

    @Autowired
    private Random random;

    @Value("${app.transfer-count}")
    private int transferCount;

    @Value("${bank.accounts-count}")
    private int accountsCount;

    @Autowired
    private TransferService transferService;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        List<Account> accounts = accountRepository.findAll();
        int currTransferCount = 0;
        while (currTransferCount < transferCount) {
            Account from = accounts.get(random.nextInt(0, accountsCount));
            Account to = accounts.get(random.nextInt(0, accountsCount));
            executor.submit(()->
            {
                try {
                    Thread.sleep(random.nextInt(1000, 2000));
                    transferService.transfer(from, to, random.nextInt() % from.getMoney());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            currTransferCount++;
        }
    }

}
