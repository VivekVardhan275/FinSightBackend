package rocks.vivek275.finsightbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import rocks.vivek275.finsightbackend.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    User getByEmail(String email);

    boolean existsByEmail(String email);

}
