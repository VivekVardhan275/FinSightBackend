package rocks.vivek275.finsightbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.ProfileWrapper;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.repo.UserRepo;

import java.sql.Date;
import java.time.OffsetDateTime;

@Service
public class ProfileService {

    @Autowired
    UserRepo userRepo;

    public ProfileWrapper getProfile(String email,String randomString) {
        String secret = randomString;
        try {
            ProfileWrapper profileWrapper = new ProfileWrapper();

            User user = userRepo.getByEmail(email);
            if (user != null) {
                profileWrapper.setDisplayName(user.getName());
                profileWrapper.setPhoneNumber(user.getPhoneNumber());
                profileWrapper.setDateOfBirth(user.getDateOfBirth().toString());
                profileWrapper.setGender(user.getGender());
            }
            return profileWrapper;
        }
        catch(Exception ex) {
            System.out.println("Error getting profile");
        }
        return null;
    }

    public boolean updateProfile(ProfileWrapper profileWrapper , String email,String randomString) {
        String secret = randomString;
        try {
            User user = userRepo.getByEmail(email);
            if (user != null) {
                user.setName(profileWrapper.getDisplayName());
                user.setPhoneNumber(profileWrapper.getPhoneNumber());
                user.setDateOfBirth(Date.valueOf(profileWrapper.getDateOfBirth()));
                user.setGender(profileWrapper.getGender());
                OffsetDateTime now = OffsetDateTime.now();
                user.setUpdatedAt(now);
                userRepo.save(user);
                return true;
            }
        }
        catch(Exception ex) {
            System.out.println("Error updating profile");
        }
        return false;
    }

}
