package com.wecollab.service;


import com.wecollab.domain.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws Exception;

    public List<User> getAllUser();

    public  List<User> getUserByCompanyId(int companyId) throws Exception;

}
