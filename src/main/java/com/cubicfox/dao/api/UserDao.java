package com.cubicfox.dao.api;

import com.cubicfox.model.User;

public interface UserDao {
    String USER_ADDRESS_ID = "user_address_id";
    String USER_COMPANY_ID = "user_company_id";
    String USER_EMAIL = "user_email";
    String USER_NAME = "user_name";
    String USER_PHONE = "user_phone";
    String USER_USERNAME = "user_username";
    String USER_WEBSITE = "user_website";
    String TABLE_NAME = "cubicfox_user";
    User saveUser(User user);
    void clearAll();
}
