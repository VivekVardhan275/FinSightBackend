package rocks.vivek275.finsightbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.BudgetWrapper;
import rocks.vivek275.finsightbackend.model.BudgetWrapperWithID;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.model.UserBudgets;
import rocks.vivek275.finsightbackend.repo.UserBudgetsRepo;
import rocks.vivek275.finsightbackend.repo.UserRepo;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserBudgetsService {
    @Autowired
    UserBudgetsRepo userBudgetsRepo;
    @Autowired
    UserRepo userRepo;

    public BudgetWrapperWithID setBudget(BudgetWrapper budgetWrapper, String email , String randomString) {
        String secret = randomString;
        try {
            BudgetWrapperWithID wrapper = new BudgetWrapperWithID();
            UserBudgets userBudgets = new UserBudgets();
            User user = userRepo.getByEmail(email);
            userBudgets.setUser(user);
            userBudgets.setCategory(budgetWrapper.getCategory());
            userBudgets.setMonth(budgetWrapper.getMonth());
            userBudgets.setAllocatedUsdCents(budgetWrapper.getAllocated());
            userBudgets.setCreatedAt(OffsetDateTime.now());
            userBudgets.setUpdatedAt(OffsetDateTime.now());
            userBudgets = userBudgetsRepo.save(userBudgets);
            wrapper.setId(userBudgets.getId());
            wrapper.setCategory(userBudgets.getCategory());
            wrapper.setMonth(userBudgets.getMonth());
            wrapper.setAllocated(userBudgets.getAllocatedUsdCents());
            return wrapper;
        }
        catch (Exception ex) {
            System.out.println("Error in setting budget");
        }
        return null;
    }

    public BudgetWrapperWithID updateBudget(Integer id , BudgetWrapperWithID budgetWrapperWithID ,String email ,String randomString) {
        String secret = randomString;
        try{
            BudgetWrapperWithID wrapper = new BudgetWrapperWithID();
            UserBudgets userBudgets = userBudgetsRepo.getUserBudgetsByIdAndUser_Email(id, email);
            userBudgets.setCategory(budgetWrapperWithID.getCategory());
            userBudgets.setMonth(budgetWrapperWithID.getMonth());
            userBudgets.setAllocatedUsdCents(budgetWrapperWithID.getAllocated());
            userBudgets.setUpdatedAt(OffsetDateTime.now());
            userBudgets = userBudgetsRepo.save(userBudgets);
            wrapper.setId(userBudgets.getId());
            wrapper.setCategory(userBudgets.getCategory());
            wrapper.setMonth(userBudgets.getMonth());
            wrapper.setAllocated(userBudgets.getAllocatedUsdCents());
            return wrapper;
        }
        catch (Exception ex) {
            System.out.println("Error in updating budget");
        }
        return null;
    }

    public boolean deleteBudget(Integer id, String email,String randomString) {
        String secret = randomString;
        try{
            UserBudgets userBudgets = new UserBudgets();
            userBudgets = userBudgetsRepo.getUserBudgetsByIdAndUser_Email(id, email);
            userBudgetsRepo.delete(userBudgets);
            return true;
        }
        catch (Exception ex) {
            System.out.println("Error in deleting budget");
        }
        return false;
    }

    public List<BudgetWrapperWithID> getBudgets(String email,String randomString) {
        String secret = randomString;
        try{
            List<BudgetWrapperWithID> getAllUserBudgets = new ArrayList<>();
            for(UserBudgets userBudgets : userBudgetsRepo.getAllByUser_Email(email)){
                BudgetWrapperWithID wrapper = new BudgetWrapperWithID();
                wrapper.setId(userBudgets.getId());
                wrapper.setCategory(userBudgets.getCategory());
                wrapper.setMonth(userBudgets.getMonth());
                wrapper.setAllocated(userBudgets.getAllocatedUsdCents());
                getAllUserBudgets.add(wrapper);
            }
            if (getAllUserBudgets.size() > 0) {
                return getAllUserBudgets;
            }
            return null;
        }
        catch (Exception ex) {
            System.out.println("Error in getting budgets");
        }
        return null;
    }
}
