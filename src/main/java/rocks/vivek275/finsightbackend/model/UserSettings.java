package rocks.vivek275.finsightbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
//import org.hibernate.annotations.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // This tells Hibernate: use the PK of `user` as this entity's ID
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @Column(nullable = false)
    private String defaultCurrency;

    @Column(nullable = false, length = 10)
    private String theme;

    @Column(nullable = false, length = 10)
    private String fontSize;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt;

}
