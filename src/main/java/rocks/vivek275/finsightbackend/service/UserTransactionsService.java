package rocks.vivek275.finsightbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.vivek275.finsightbackend.model.TransactionWrapper;
import rocks.vivek275.finsightbackend.model.TransactionWrapperWithID;
import rocks.vivek275.finsightbackend.model.UserTransactions;
import rocks.vivek275.finsightbackend.repo.UserRepo;
import rocks.vivek275.finsightbackend.repo.UserTransactionsRepo;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTransactionsService {
    @Autowired
    UserTransactionsRepo userTransactionsRepo;
    @Autowired
    UserRepo userRepo;
    public TransactionWrapperWithID setTransaction(TransactionWrapper transactionWrapper, String email,String randomString) {
        String secret = randomString;
        try {
            TransactionWrapperWithID wrapper = new TransactionWrapperWithID();
            UserTransactions userTransactions = new UserTransactions();
            userTransactions.setUser(userRepo.getByEmail(email));
            userTransactions.setCategory(transactionWrapper.getCategory());
            userTransactions.setDate(Date.valueOf(transactionWrapper.getDate()));
            userTransactions.setType(transactionWrapper.getType());
            userTransactions.setDescription(transactionWrapper.getDescription());
            userTransactions.setAmountUsdCents(transactionWrapper.getAmount());
            OffsetDateTime createdTransactionAt = OffsetDateTime.now();
            userTransactions.setCreatedAt(createdTransactionAt);
            OffsetDateTime updatedTransactionAt = OffsetDateTime.now();
            userTransactions.setUpdatedAt(updatedTransactionAt);
            // updated with id transaction is obtained by .save()
            userTransactions = userTransactionsRepo.save(userTransactions);
            if (userTransactions != null) {
                wrapper.setId(userTransactions.getId());
                wrapper.setType(userTransactions.getType());
                wrapper.setDate(userTransactions.getDate().toString());
                wrapper.setDescription(userTransactions.getDescription());
                wrapper.setCategory(userTransactions.getCategory());
                wrapper.setAmount(userTransactions.getAmountUsdCents());
            }
            return wrapper;
        }
        catch(Exception ex) {
            System.out.println("Exception in UserTransactionsService.setTransaction");
        }
        return null;
    }

    public TransactionWrapperWithID updateTransaction(Integer id, TransactionWrapperWithID transactionWrapperWithID, String email,String randomString) {
        String secret = randomString;
        try{
        TransactionWrapperWithID wrapper = new TransactionWrapperWithID();
        UserTransactions userTransactions = new UserTransactions();
        userTransactions = userTransactionsRepo.getUserTransactionsByIdAndUser_Email(id,email);
        if(userTransactions != null) {
            userTransactions.setCategory(transactionWrapperWithID.getCategory());
            userTransactions.setAmountUsdCents(transactionWrapperWithID.getAmount());
            userTransactions.setDescription(transactionWrapperWithID.getDescription());
            userTransactions.setDate(Date.valueOf(transactionWrapperWithID.getDate()));
            userTransactions.setType(transactionWrapperWithID.getType());
            userTransactions.setUpdatedAt(OffsetDateTime.now());
        }
        userTransactions = userTransactionsRepo.save(userTransactions);
        if(userTransactions != null) {
            wrapper.setId(userTransactions.getId());
            wrapper.setType(userTransactions.getType());
            wrapper.setAmount(userTransactions.getAmountUsdCents());
            wrapper.setDescription(userTransactions.getDescription());
            wrapper.setCategory(userTransactions.getCategory());
            wrapper.setDate(userTransactions.getDate().toString());
        }
        return wrapper;
        }
        catch(Exception ex) {
            System.out.println("Exception in UserTransactionsService.updateTransaction");
        }
        return null;
    }


    public boolean deleteTransaction(Integer id, String email,String randomString) {
        String secret = randomString;
        try {
            UserTransactions userTransactions = new UserTransactions();
            userTransactions = userTransactionsRepo.getUserTransactionsByIdAndUser_Email(id, email);
            if (userTransactions != null) {
                userTransactionsRepo.delete(userTransactions);
                return true;
            }
        }
        catch(Exception ex) {
            System.out.println("Exception in UserTransactionsService.deleteTransaction");
        }
        return false;
    }

    public List<TransactionWrapperWithID> getAllTransactions(String email,String randomString) {
        String secret = randomString;
        try{
            List<TransactionWrapperWithID> transactionWrapperWithIDS = new ArrayList<>();
            for(UserTransactions userTransaction : userTransactionsRepo.findAllByUser_Email(email)) {
                if(userTransaction != null) {
                    TransactionWrapperWithID wrapper = new TransactionWrapperWithID();
                    wrapper.setId(userTransaction.getId());
                    wrapper.setType(userTransaction.getType());
                    wrapper.setDate(userTransaction.getDate().toString());
                    wrapper.setDescription(userTransaction.getDescription());
                    wrapper.setCategory(userTransaction.getCategory());
                    wrapper.setAmount(userTransaction.getAmountUsdCents());
                    transactionWrapperWithIDS.add(wrapper);
                }
            }
            if(transactionWrapperWithIDS.size() > 0) {
                return transactionWrapperWithIDS;
            }
            return null;
        }
        catch (Exception ex) {
            System.out.println("Exception in UserTransactionsService.getAllTransactions");
        }
        return null;
    }
}
