package rocks.vivek275.finsightbackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.model.UserBudgets;

import java.util.List;

@Repository
public interface UserBudgetsRepo extends JpaRepository<UserBudgets, Integer> {
    UserBudgets getUserBudgetsByIdAndUser_Email(Integer id, String userEmail);

    UserBudgets[] getAllByUser_Email(String email);

    List<UserBudgets> getAllByUser(User user);
}
