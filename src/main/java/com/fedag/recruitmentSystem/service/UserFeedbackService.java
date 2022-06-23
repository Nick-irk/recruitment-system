package com.fedag.recruitmentSystem.service;

import java.util.List;

import com.fedag.recruitmentSystem.dto.response.user_response.UserFeedbackResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFeedbackService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllUserFeedbacks();

  Page<T> getAllUserFeedbacks(Pageable pageable);

    Page<UserFeedbackResponseForUser> getAllUserFeedbacksForUser(Pageable pageable);

  UserFeedbackResponseForUser findByIdForUser(Long id);
}
