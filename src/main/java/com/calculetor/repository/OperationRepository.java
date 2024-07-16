package com.calculetor.repository;

import com.calculetor.model.Operation;
import com.calculetor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Integer> {
   Operation findByUser(User user);
}
