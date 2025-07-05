package com.user.user_service.services;

import com.user.user_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService{

    //Fake user list

    List<User> list = List.of(
            new User(1311L,"Jagadish Pradhan","0000000000"),
            new User(1312L,"Sumi Ojha","9999999999"),
            new User(1313L,"Mr. Rohan","8888888888")
    );

    @Override
    public User getUser(Long id) {
      //  return list.stream().filter(u->u.getUserId()==id).findAny().orElse(null);


       return list.stream().filter(User -> User.getUserId()
                == id).findAny().orElse(null);
    }
}
