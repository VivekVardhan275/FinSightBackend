package rocks.vivek275.finsightbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.repo.UserRepo;

@Service
public class FirstCheckService {
    @Autowired
    UserRepo userRepo;
    public boolean firstCheck(String email , String randomString) {
        try {
            String secret = randomString;
            User user = userRepo.getByEmail(email);
            System.out.println(user);
            if (user == null) {
                return false;
            } else if (user.isHasCompletedSetup()) {
                return true;
            }
        }
        catch(Exception ex) {
            System.out.println("First check failed");
        }
        return false;
    }
}
