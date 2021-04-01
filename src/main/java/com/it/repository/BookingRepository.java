package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer>{

}
