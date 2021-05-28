package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.UserAccountConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountConfirmationDAO extends JpaRepository<UserAccountConfirmation, String> {
    UserAccountConfirmation findFirstByCode(String code);
}
