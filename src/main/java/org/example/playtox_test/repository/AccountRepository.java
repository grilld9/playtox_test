package org.example.playtox_test.repository;

import org.example.playtox_test.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
