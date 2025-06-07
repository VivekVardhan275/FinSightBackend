package rocks.vivek275.finsightbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import rocks.vivek275.finsightbackend.model.User;
import rocks.vivek275.finsightbackend.repo.UserRepo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@SpringBootApplication
public class FinSightBackendApplication {
    public static void main(String[] args) {
       ApplicationContext applicationContext =   SpringApplication.run(FinSightBackendApplication.class, args);
//        User user = applicationContext.getBean(User.class);
//        user.setName("Vivek Majji");
//        user.setEmail("kavithamajji106@gmail.com");
//        user.setPhoneNumber("+91 8639283469");
//        user.setGender("Male");
//        user.setDateOfBirth(Date.valueOf(LocalDate.of(2006,05,27)));
//        user.setHasCompletedSetup(true);
//        OffsetDateTime now = OffsetDateTime.now();
//        user.setCreatedAt(now);
//        user.setUpdatedAt(now);
//        UserRepo userRepo = applicationContext.getBean(UserRepo.class);
//        userRepo.save(user);
    }
}
