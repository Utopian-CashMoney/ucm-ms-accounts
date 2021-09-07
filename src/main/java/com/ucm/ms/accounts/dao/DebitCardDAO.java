package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardDAO extends JpaRepository<DebitCard, Integer> {
}
