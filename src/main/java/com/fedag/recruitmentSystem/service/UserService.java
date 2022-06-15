package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllUsers();

    Page<T> getAllUsers(Pageable pageable);

    boolean activateUser(String code);
}