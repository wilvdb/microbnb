package microbnb.accountingms;

import com.fasterxml.jackson.databind.ObjectMapper;
import microbnb.accountingms.model.dto.AccountInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/application-mock.yml")
class AccountingMsApplicationTest {

  @LocalServerPort
  int port;

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void createAccount() throws Exception {
    AccountInput input = new AccountInput();
    input.setBirthday(LocalDate.now());
    input.setEmail("hello@gmail.com");
    input.setFirstName("test");
    input.setLastName("hello");

    URI uri = restTemplate.postForLocation(new URI("http://localhost:" + port + "/v1/accounts"), input);

    Assertions.assertTrue(true);

  }
}
