package rocks.vivek275.finsightbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import rocks.vivek275.finsightbackend.model.UserTransactions;

@Repository
public interface UserTransactionsRepo extends JpaRepository<UserTransactions, Integer> {
    UserTransactions getUserTransactionsByIdAndUser_Email(Integer id, String email);

    UserTransactions[] findAllByUser_Email(String userEmail);

    UserTransactions getAllByUser_Email(String userEmail);
}
