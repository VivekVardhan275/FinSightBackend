package rocks.vivek275.finsightbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.repo.UserRepo;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
}
