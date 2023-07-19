package pl.dziewulskil.AplikacjaRestowa.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.dziewulskil.AplikacjaRestowa.audit.AbstractTimeAuditable;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyEntity extends AbstractTimeAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "firstname", nullable = false, length = 20)
    String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    String lastname;

    @Column(name = "code", nullable = false, length = 3)
    String code;

    @Column(name = "currency", nullable = false, length = 20)
    String currency;

    @Column(name = "value", nullable = false, length = 6)
    double value;
}
