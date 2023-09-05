package com.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.picpaysimplificado.domain.transaction.Transaction;
public interface TransctionRepository extends JpaRepository <Transaction, Long> {
}
