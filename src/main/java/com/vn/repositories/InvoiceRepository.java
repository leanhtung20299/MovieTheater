package com.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long >{

}
