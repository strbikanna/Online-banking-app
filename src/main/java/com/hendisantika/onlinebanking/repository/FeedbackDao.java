package com.hendisantika.onlinebanking.repository;

import com.hendisantika.onlinebanking.entity.Feedback;
import com.hendisantika.onlinebanking.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackDao extends CrudRepository<Feedback, Long> {

    List<Feedback> findAll();

    List<Feedback> findByUserOrderByCreatedAtDesc(User user);
}
