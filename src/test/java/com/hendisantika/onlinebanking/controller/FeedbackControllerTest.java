package com.hendisantika.onlinebanking.controller;

import com.hendisantika.onlinebanking.entity.Feedback;
import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.service.FeedbackService;
import com.hendisantika.onlinebanking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {

    @Mock
    private FeedbackService feedbackService;

    @Mock
    private UserService userService;

    @InjectMocks
    private FeedbackController feedbackController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

    @Test
    void createFeedbackPage_returnsForm() throws Exception {
        mockMvc.perform(get("/feedback/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("feedback"))
                .andExpect(model().attributeExists("feedback"));
    }

    @Test
    void submitFeedback_withValidData_savesAndShowsSuccess() throws Exception {
        User user = new User();
        when(userService.findByUsername("testuser")).thenReturn(user);
        when(feedbackService.submitFeedback(any(Feedback.class))).thenAnswer(inv -> inv.getArgument(0));

        mockMvc.perform(post("/feedback/create")
                        .param("subject", "Great app")
                        .param("message", "I really like this banking app!")
                        .param("rating", "5")
                        .principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(view().name("feedback"))
                .andExpect(model().attributeExists("successMessage"));

        verify(feedbackService, times(1)).submitFeedback(any(Feedback.class));
    }

    @Test
    void submitFeedback_withMissingFields_showsErrors() throws Exception {
        mockMvc.perform(post("/feedback/create")
                        .param("subject", "")
                        .param("message", "")
                        .param("rating", "0")
                        .principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(view().name("feedback"));

        verify(feedbackService, never()).submitFeedback(any(Feedback.class));
    }
}
