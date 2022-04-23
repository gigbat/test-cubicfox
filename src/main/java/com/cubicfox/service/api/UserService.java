package com.cubicfox.service.api;

import com.cubicfox.model.User;

import java.util.List;

public interface UserService {
    List<User> saveUsers(List<User> users) throws Exception;
    void clearAll();
}
