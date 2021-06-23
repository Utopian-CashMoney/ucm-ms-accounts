package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDAO extends JpaRepository<Card, Integer> {
}
