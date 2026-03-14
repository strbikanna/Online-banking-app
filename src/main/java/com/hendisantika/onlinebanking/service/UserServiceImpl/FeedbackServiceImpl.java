package com.hendisantika.onlinebanking.service.UserServiceImpl;

import com.hendisantika.onlinebanking.entity.Feedback;
import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.repository.FeedbackDao;
import com.hendisantika.onlinebanking.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        return feedbackDao.save(feedback);
    }

    @Override
    public List<Feedback> findByUser(User user) {
        return feedbackDao.findByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackDao.findAll();
    }
}
