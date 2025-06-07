package rocks.vivek275.finsightbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetWrapper {
    private String category;
    private long allocated;
    private String month;
}
