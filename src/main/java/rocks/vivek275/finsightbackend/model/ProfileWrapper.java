package rocks.vivek275.finsightbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProfileWrapper {
    private String displayName;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;

    @Override
    public String toString() {
        return "ProfileWrapper{" +
                "displayName='" + displayName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
