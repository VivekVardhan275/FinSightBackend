package rocks.vivek275.finsightbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.vivek275.finsightbackend.model.*;
import rocks.vivek275.finsightbackend.service.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    FirstCheckService firstCheckService;
    @Autowired
    InitialSetUpService initialSetUpService;
    @Autowired
    ProfileService profileService;
    @Autowired
    UserSettingsService userSettingsService;
    @Autowired
    UserTransactionsService userTransactionsService;
    @Autowired
    UserBudgetsService userBudgetsService;
    @Autowired
    UserService userService;

    @GetMapping("/first-check")
    public ResponseEntity<FirstCheckWrapper> firstCheck(@RequestParam String email , @RequestParam String _cb) {
//        System.out.println(email);
        FirstCheckWrapper firstCheckWrapper = new FirstCheckWrapper();
         boolean check =  firstCheckService.firstCheck(email,_cb);
        firstCheckWrapper.setHasCompletedSetup(check);
        return new ResponseEntity<>(firstCheckWrapper, HttpStatus.OK);
    }

    @PostMapping("/setup")
    public ResponseEntity<String> setup(@RequestBody SetUpWrapper setUpWrapper, @RequestParam String _cb) {
//            System.out.println(setUpWrapper);
            return initialSetUpService.intializeFirstSetUp(setUpWrapper,_cb);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileWrapper> profile(@RequestParam String email, @RequestParam String _cb) {
        ProfileWrapper profileWrapper = new ProfileWrapper();
        profileWrapper = profileService.getProfile(email,_cb);
        if(profileWrapper == null) {
            return new ResponseEntity<>(profileWrapper, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileWrapper, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileWrapper profileWrapper , @RequestParam String email, @RequestParam String _cb) {
        boolean check = profileService.updateProfile(profileWrapper,email,_cb);
        if(check) {
            return new ResponseEntity<>("Profile updated successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Profile not updated",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/settings")
    public ResponseEntity<SettingsWrapper> getSettings(@RequestParam String email, @RequestParam String _cb) {
        SettingsWrapper settingsWrapper = new SettingsWrapper();
        settingsWrapper = userSettingsService.getUserSettings(email,_cb);
        if (settingsWrapper == null) {
            return new ResponseEntity<>(settingsWrapper, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(settingsWrapper, HttpStatus.OK);
    }
    @PutMapping("/settings")
    public ResponseEntity<String> updateSettings(@RequestBody SettingsWrapper settingsWrapper , @RequestParam String email, @RequestParam String _cb) {
        boolean check = userSettingsService.updateSettings(settingsWrapper,email,_cb);
        if(check) {
            return new ResponseEntity<>("Settings updated successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Settings not updated",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String email ,@RequestParam String confirmationCode, @RequestParam String _cb ) {
        boolean check = userSettingsService.deleteAccount(email,confirmationCode,_cb);
        if(check) {
            return new ResponseEntity<>("Account deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Account not deleted",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users = userService.findAllUsers();
        return users;
    }
    @GetMapping("/budgets")
    public ResponseEntity<List<BudgetWrapperWithID>> getBudgets(@RequestParam String email, @RequestParam String _cb) {
        List<BudgetWrapperWithID> budgets = userBudgetsService.getBudgets(email,_cb);
        if(budgets == null) {
            return new ResponseEntity<>(budgets, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }
    @PostMapping("/budgets")
    public ResponseEntity<BudgetWrapperWithID> setBudget(@RequestBody BudgetWrapper budgetWrapper , @RequestParam String email, @RequestParam String _cb) {
        BudgetWrapperWithID budgetWrapperWithID = new BudgetWrapperWithID();
        budgetWrapperWithID = userBudgetsService.setBudget(budgetWrapper,email,_cb);
        if (budgetWrapper != null) {
            return new ResponseEntity<>(budgetWrapperWithID, HttpStatus.OK);
        }
        return new ResponseEntity<>(budgetWrapperWithID, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/budgets/{id}")
    public ResponseEntity<BudgetWrapperWithID> updateBudget( @PathVariable Integer id, @RequestBody BudgetWrapperWithID budgetWrapperWithID , @RequestParam String email, @RequestParam String _cb) {
        BudgetWrapperWithID updatedBudgetWrapperWithID = new BudgetWrapperWithID();
        updatedBudgetWrapperWithID = userBudgetsService.updateBudget(id, budgetWrapperWithID,email,_cb);
        if (updatedBudgetWrapperWithID != null) {
            return new ResponseEntity<>(updatedBudgetWrapperWithID, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedBudgetWrapperWithID, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/budgets/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Integer id, @RequestParam String email, @RequestParam String _cb) {
        boolean check = userBudgetsService.deleteBudget(id,email,_cb);
        if(check) {
            return new ResponseEntity<>("Budget deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Budget not deleted",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionWrapperWithID>> getTransactions(@RequestParam String email, @RequestParam String _cb) {
        List<TransactionWrapperWithID> transactionWrappersWithID = userTransactionsService.getAllTransactions(email,_cb);
        if(transactionWrappersWithID == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionWrappersWithID, HttpStatus.OK);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionWrapperWithID> updateTransaction(@PathVariable Integer id , @RequestBody TransactionWrapperWithID transactionWrapperWithID, @RequestParam String email, @RequestParam String _cb) {
        TransactionWrapperWithID updatedTransactionWrapperWithID = new TransactionWrapperWithID();
        updatedTransactionWrapperWithID = userTransactionsService.updateTransaction(id,transactionWrapperWithID,email,_cb);
        if (updatedTransactionWrapperWithID != null) {
            return new ResponseEntity<>(updatedTransactionWrapperWithID, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id, @RequestParam String email, @RequestParam String _cb) {
        boolean check = userTransactionsService.deleteTransaction(id,email,_cb);
        if(check) {
            return new ResponseEntity<>("Transaction deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Transaction not deleted",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionWrapperWithID> setTransaction(@RequestBody TransactionWrapper transactionWrapper , @RequestParam String email, @RequestParam String _cb) {
        TransactionWrapperWithID transactionWrapperWithID = new TransactionWrapperWithID();
        transactionWrapperWithID = userTransactionsService.setTransaction(transactionWrapper, email,_cb);
        if (transactionWrapperWithID != null) {
            return new ResponseEntity<>(transactionWrapperWithID, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
