package com.jmurphey.stackoverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmurphey.stackoverflow.models.Tag;

@Repository
public interface TagRepo extends CrudRepository<Tag, Long>{

//	public Optional <Tag> findBySubject(String subject);
	
	Tag findBySubject(String subject);
	boolean existsBySubject(String subject);
	List<Tag> findAll();
	
}
