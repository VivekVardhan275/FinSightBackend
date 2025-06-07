package rocks.vivek275.finsightbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import rocks.vivek275.finsightbackend.model.UserSettings;

@Repository
public interface UserSettingsRepo extends JpaRepository<UserSettings, String> {
    UserSettings getUserSettingsByUserEmail(String email);
}
