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
    public SettingsWrapper getUserSettings(String email,String randomString) {
        String secret = randomString;
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

    public boolean updateSettings(SettingsWrapper settingsWrapper, String email,String randomString) {
        String secret = randomString;
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

    public boolean deleteAccount(String email, String confirmationCode, String randomString) {
        try {
            User user = userRepo.getByEmail(email);
            List<UserBudgets> userBudgets = userBudgetsRepo.getAllByUser(user);
            if (userBudgets != null && !userBudgets.isEmpty()) {
                userBudgetsRepo.deleteAll(userBudgets);
            }
            List<UserTransactions> userTransactions = userTransactionsRepo.getAllByUser(user);
            if (userTransactions != null && !userTransactions.isEmpty()) {
                userTransactionsRepo.deleteAll(userTransactions);
            }
            UserSettings userSettings = userSettingsRepo.getUserSettingsByUserEmail(email);
            if (userSettings != null) {
                userSettingsRepo.delete(userSettings);
            }
            User users = userRepo.getByEmail(email);
            if (users != null) {
                userRepo.delete(user);
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Error deleting account: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
