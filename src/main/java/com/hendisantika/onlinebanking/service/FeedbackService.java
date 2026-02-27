package com.hendisantika.onlinebanking.service;

import com.hendisantika.onlinebanking.entity.Feedback;
import com.hendisantika.onlinebanking.entity.User;

import java.util.List;

public interface FeedbackService {

    Feedback submitFeedback(Feedback feedback);

    List<Feedback> findByUser(User user);

    List<Feedback> findAll();
}
