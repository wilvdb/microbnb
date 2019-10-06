package microbnb.accountingms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class AccountingMsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountingMsApplication.class, args);
  }
}
