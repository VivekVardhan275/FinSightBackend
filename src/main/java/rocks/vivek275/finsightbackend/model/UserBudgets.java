package rocks.vivek275.finsightbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "budgets",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_user_category_month", columnNames = {"user_email", "category", "month"})
        },
        indexes = {
                @Index(name = "idx_user_month", columnList = "user_email, month")
        })
public class UserBudgets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false)
    private Long allocatedUsdCents;

    @Column(nullable = false, length = 7)
    private String month;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Override
    public String toString() {
        return "UserBudgets{" +
                "id=" + id +
                ", user=" + user +
                ", category='" + category + '\'' +
                ", allocatedUsdCents=" + allocatedUsdCents +
                ", month='" + month + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
