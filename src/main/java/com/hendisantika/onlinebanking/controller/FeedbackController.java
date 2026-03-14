package com.hendisantika.onlinebanking.controller;

import com.hendisantika.onlinebanking.entity.Feedback;
import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.service.FeedbackService;
import com.hendisantika.onlinebanking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    public FeedbackController(FeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createFeedback(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/create")
    public String submitFeedback(@Valid @ModelAttribute("feedback") Feedback feedback,
                                 BindingResult result,
                                 Model model,
                                 Principal principal) {
        if (result.hasErrors()) {
            return "feedback";
        }

        User user = userService.findByUsername(principal.getName());
        feedback.setUser(user);
        feedback.setCreatedAt(LocalDateTime.now());
        feedbackService.submitFeedback(feedback);

        model.addAttribute("successMessage", "Thank you for your feedback!");
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }
}
