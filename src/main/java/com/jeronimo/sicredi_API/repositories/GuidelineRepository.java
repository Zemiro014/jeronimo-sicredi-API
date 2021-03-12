package com.jeronimo.sicredi_API.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jeronimo.sicredi_API.domain.Guideline;

@Repository
public interface GuidelineRepository extends MongoRepository<Guideline, String>{

}
