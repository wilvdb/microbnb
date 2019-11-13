package microbnb.identityms.model.dto

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.Past

data class SubmitIdentity(

        val firstName: String,
        val lastName: String,

        @Email
        val email: String,

        @Past
        val birthday: LocalDate
)