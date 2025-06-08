package rocks.vivek275.finsightbackend.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.SetUpWrapper;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.model.UserSettings;
import rocks.vivek275.finsightbackend.repo.UserRepo;
import rocks.vivek275.finsightbackend.repo.UserSettingsRepo;

import java.sql.Date;
import java.time.OffsetDateTime;

@Service
public class InitialSetUpService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserSettingsRepo userSettingsRepo;
    @Transactional
    public ResponseEntity<String> intializeFirstSetUp(SetUpWrapper setUpWrapper,String randomString) {
        String secret = randomString;
        try {
            if (userRepo.existsByEmail(setUpWrapper.getEmail())) {
                return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            }
            // creating user
            User newUser = new User();
            newUser.setEmail(setUpWrapper.getEmail());
            newUser.setName(setUpWrapper.getDisplayName());
            newUser.setPhoneNumber(setUpWrapper.getPhoneNumber());
            newUser.setDateOfBirth(Date.valueOf(setUpWrapper.getDateOfBirth()));
            newUser.setGender(setUpWrapper.getGender());
            newUser.setHasCompletedSetup(true);
            OffsetDateTime userCreatedAt = OffsetDateTime.now();
            newUser.setCreatedAt(userCreatedAt);
            OffsetDateTime userUpdatedAt = OffsetDateTime.now();
            newUser.setUpdatedAt(userUpdatedAt);
            // creating user settings
            UserSettings newUserSettings = new UserSettings();
            newUserSettings.setUser(newUser);
            newUserSettings.setTheme(setUpWrapper.getTheme());
            newUserSettings.setFontSize(setUpWrapper.getFontSize());
            newUserSettings.setDefaultCurrency(setUpWrapper.getCurrency());
            OffsetDateTime userSettingsUpdatedAt = OffsetDateTime.now();
            newUserSettings.setUpdatedAt(userSettingsUpdatedAt);
            // saving to repository
            userSettingsRepo.save(newUserSettings);
            userRepo.save(newUser);
            return new ResponseEntity<>("Setup completed successfully", HttpStatus.OK);
        }
        catch (Exception ex) {
            System.out.println("InitializeFirstSetUp Exception: " + ex.getMessage());
        }
        return new ResponseEntity<>("InitializeFirstSetUp Failed", HttpStatus.CONFLICT);
    }
}
