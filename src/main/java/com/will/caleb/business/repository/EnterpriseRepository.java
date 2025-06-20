package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer>, JpaSpecificationExecutor<Enterprise> {

    @Query(value = """
            select * from bus_enterprise
             where cnpj = :cnpj
            """, nativeQuery = true)
    List<Enterprise> findAllByCnpj(String cnpj);

    @Query(value = """
            select * from bus_enterprise
             where email = :email
            """, nativeQuery = true)
    List<Enterprise> findAllByEmail(String email);


}
