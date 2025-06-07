package rocks.vivek275.finsightbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstCheckWrapper {
    private boolean hasCompletedSetup;

    @Override
    public String toString() {
        return "FirstCheckWrapper{" +
                "hasCompletedSetup=" + hasCompletedSetup +
                '}';
    }
}
