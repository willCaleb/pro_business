package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

    Page<Client> findAllByEnterprise(Integer enterprise, Pageable pageable);

    Optional<Client> findByEmailAndEnterprise(String email, Integer enterpeise);

    Optional<Client> findByDocumentAndEnterprise(String document, Integer enterprise);

}
