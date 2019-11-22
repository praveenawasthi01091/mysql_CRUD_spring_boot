package com.wecollab.service;


import com.wecollab.domain.User;
import com.wecollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
// mvn clean
// mvn spring-boot:run -Dspring.profiles.active=dummy
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        System.out.println("Inside actual impl");
        this.userRepository=userRepository;
    }



    @Override
    public User saveUser(User user) throws Exception {
        if(userRepository.existsById(user.getId()))
            throw new Exception("User id is already exists");
        User savedUser= userRepository.save(user);
        if(savedUser == null)
            throw new Exception("Track id is already exists");
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByCompanyId(int companyId) throws Exception {
        List<User>list= userRepository.findBycompanyId(companyId);
        return list;
    }
}