-- Create feedback table
CREATE TABLE feedback (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_user_id BIGINT,
    subject VARCHAR(100) NOT NULL,
    message VARCHAR(1000) NOT NULL,
    rating INT NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (user_user_id) REFERENCES user(user_id)
);

CREATE INDEX idx_feedback_user ON feedback(user_user_id);
CREATE INDEX idx_feedback_created_at ON feedback(created_at);
