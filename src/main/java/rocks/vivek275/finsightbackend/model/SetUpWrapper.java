package rocks.vivek275.finsightbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetUpWrapper {
    private String email;
    private String displayName;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private String theme;
    private String fontSize;
    private String currency;

    @Override
    public String toString() {
        return "SetUpWrapper{" +
                "email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", theme='" + theme + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
