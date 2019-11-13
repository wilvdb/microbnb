package microbnb.identityms.model.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "IDENTITY")
data class Identity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "IDEN_ID")
        val id: Long,

        @Column(name = "IDEN_FIRST_NAME", nullable = false)
        val firstName: String,

        @Column(name = "ACC_LAST_NAME", nullable = false)
        val lastName: String,

        @Column(name = "ACC_EMAIL", nullable = false)
        val email: String,

        @Column(name = "IDEN_BIRTHDAY", nullable = false)
        val birthday: LocalDate
)