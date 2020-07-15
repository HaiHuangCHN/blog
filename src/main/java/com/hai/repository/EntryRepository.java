package com.hai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hai.entity.Entry;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Integer> {

	@Query(value = "select * from blog.entries", nativeQuery = true)
	public List<Entry> finaAll();

}