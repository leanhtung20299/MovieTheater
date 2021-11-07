package com.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Ticket;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
