package rocks.vivek275.finsightbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsWrapper {
    private String theme;
    private String fontSize;
    private String currency;

    @Override
    public String toString() {
        return "SettingsWrapper{" +
                "theme='" + theme + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
