package com.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Type;
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
