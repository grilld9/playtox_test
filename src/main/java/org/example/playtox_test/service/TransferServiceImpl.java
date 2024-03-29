package org.example.playtox_test.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.playtox_test.entity.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {


    private int transferId = 0;
    @Transactional
    @NonNull
    public void transfer(Account from, Account to, Long amount) {
        if (amount <= 0) {
            log.warn("Операция #{}: Сумма перевода должна быть положительной", transferId);
            transferId++;
            throw new IllegalArgumentException();
        }
        if (from.getMoney() < amount) {
            log.warn("Операция #{}: На счете id={} не хватает {} денег", transferId, from.getId(), amount - from.getMoney());
            transferId++;
            throw new IllegalStateException();
        }
        from.setMoney(from.getMoney() - amount);
        to.setMoney(to.getMoney() + amount);
        log.info("Операция #{}: Со счета id={} на счет id={} было перевдено {} денег", transferId, from.getId(), to.getId(), amount);
        transferId++;
    }
}
