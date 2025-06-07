package rocks.vivek275.finsightbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.*;
import rocks.vivek275.finsightbackend.repo.UserBudgetsRepo;
import rocks.vivek275.finsightbackend.repo.UserRepo;
import rocks.vivek275.finsightbackend.repo.UserSettingsRepo;
import rocks.vivek275.finsightbackend.repo.UserTransactionsRepo;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UserSettingsService {
    @Autowired
    UserSettingsRepo userSettingsRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserTransactionsRepo userTransactionsRepo;
    @Autowired
    UserBudgetsRepo userBudgetsRepo;
    public SettingsWrapper getUserSettings(String email) {
        try {

        SettingsWrapper settings = new SettingsWrapper();
        UserSettings userSettings = userSettingsRepo.getUserSettingsByUserEmail(email);
        if(userSettings != null) {
            settings.setTheme(userSettings.getTheme());
            settings.setFontSize(userSettings.getFontSize());
            settings.setCurrency(userSettings.getDefaultCurrency());
        }
        return settings;
        }
        catch(Exception ex) {
            System.out.println("Error getting user settings");
        }
        return null;
    }

    public boolean updateSettings(SettingsWrapper settingsWrapper, String email) {
        try {
            UserSettings userSettings = userSettingsRepo.getUserSettingsByUserEmail(email);
            if (userSettings != null) {
                userSettings.setTheme(settingsWrapper.getTheme());
                userSettings.setFontSize(settingsWrapper.getFontSize());
                userSettings.setDefaultCurrency(settingsWrapper.getCurrency());
                OffsetDateTime updatedTime = OffsetDateTime.now();
                userSettings.setUpdatedAt(updatedTime);
                userSettingsRepo.save(userSettings);
                return true;
            }
        }
        catch(Exception ex) {
            System.out.println("Error updating user settings");
        }
        return false;
    }

    public boolean deleteAccount(String email, String confirmationCode) {
        String confirmation = confirmationCode;
        System.out.println(confirmationCode);
        try {
            try {
                List<UserBudgets> userBudgets = List.of(userBudgetsRepo.getAllByUser_Email(email));
                if (userBudgets != null) {
                    userBudgetsRepo.deleteAll(userBudgets);
                }
            }
            catch(Exception ex) {
                System.out.println("Error deleting user budgets");
            }
            try{
                List<UserTransactions> userTransactions = List.of(userTransactionsRepo.getAllByUser_Email(email));
                if (userTransactions != null) {
                    userTransactionsRepo.deleteAll(userTransactions);
                }
            }
            catch(Exception ex) {
                System.out.println("Error deleting user transactions");
            }
            try {
                List<UserSettings> userSettings = List.of(userSettingsRepo.getUserSettingsByUserEmail(email));
                if (userSettings != null) {
                    userSettingsRepo.deleteAll(userSettings);
                }
            }
            catch(Exception ex) {
                System.out.println("Error deleting user settings");
            }
            try {
                List<User> users = List.of(userRepo.getByEmail(email));
                if (users != null) {
                    userRepo.deleteAll(users);
                }
            }
            catch(Exception ex) {
                System.out.println("Error deleting user");
            }
            return true;
        }
        catch(Exception ex) {
            System.out.println("Error deleting account");
        }
        return false;
    }
}
