package microbnb.accountingms.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Valid
public class AccountInput {

    @NotNull
    @Pattern(regexp = "\\w{0,50}")
    String firstName;

    @NotNull
    @Pattern(regexp = "\\w{0,50}")
    String lastName;

    @NotNull @Email String email;

    @NotNull @Past LocalDate birthday;
}
