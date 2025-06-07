package rocks.vivek275.finsightbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionWrapper {
    private String date;
    private String description;
    private String category;
    private long amount;
    private String type;

    @Override
    public String toString() {
        return "TransactionWrapper{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

}
