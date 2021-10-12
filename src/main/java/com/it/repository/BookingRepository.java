package com.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

	public Optional<List<BookingEntity>> findByBkDate(String bkDate);
	
}
