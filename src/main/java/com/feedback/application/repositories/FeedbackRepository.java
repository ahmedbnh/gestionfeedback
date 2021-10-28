package com.feedback.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.application.entities.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

}
