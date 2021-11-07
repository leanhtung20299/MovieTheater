package com.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.CinemaRoom;
@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long>{

}
