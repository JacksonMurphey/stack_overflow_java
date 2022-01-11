package com.jmurphey.stackoverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmurphey.stackoverflow.models.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long>{
	

}
