package microbnb.accountingms;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import microbnb.accountingms.model.dto.AccountInput;
import microbnb.accountingms.model.entity.Account;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/application-mock.yml")
class AccountingMsApplicationTest {

  @LocalServerPort
  int port;

  @Autowired
  TestRestTemplate restTemplate;

  @Order(1)
  @Test
  void should_create_account_when_input_is_valid() throws Exception {
    AccountInput input = new AccountInput();
    input.setBirthday(LocalDate.now());
    input.setEmail("hello@gmail.com");
    input.setFirstName("test");
    input.setLastName("hello");

    URI uri = restTemplate.postForLocation(new URI("http://localhost:" + port + "/v1/accounts"), input);

    assertNotNull(uri);
  }

  @Order(2)
  @Test
  void should_not_found_account_when_looking_for_not_existing_account() {
    ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:" + port + "/v1/accounts/1", Account.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Order(3)
  @Test
  void should_not_retrieve_any_account() {
    ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/v1/accounts", List.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
