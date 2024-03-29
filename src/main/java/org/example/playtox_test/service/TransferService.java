package org.example.playtox_test.service;

import org.example.playtox_test.entity.Account;

public interface TransferService {

    /**
     * Перевести деньги с одного счета на другой
     * @param from счет, от куда списываются деньги
     * @param to счет, куда зачисляются деньги
     * @param amount сумма
     */
    void transfer(Account from, Account to, Long amount);
}
