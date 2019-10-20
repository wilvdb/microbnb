package microbnb.accountingms.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID")
    long id;

    @Column(name = "ACC_FIRST_NAME")
    String firstName;

    @Column(name = "ACC_LAST_NAME")
    String lastName;

    @Column(name = "ACC_EMAIL")
    String email;

    @Column(name = "")
    LocalDate birthday;
}
