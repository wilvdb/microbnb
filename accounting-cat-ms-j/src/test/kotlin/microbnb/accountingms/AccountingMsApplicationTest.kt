package microbnb.accountingms

import io.vavr.collection.List
import microbnb.accountingms.model.dto.AccountInput
import microbnb.accountingms.model.dto.ResponseData
import microbnb.accountingms.model.entity.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URI
import java.time.LocalDate

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:/application-mock.yml"])
class AccountingMsApplicationTest(@Autowired val restTemplate: TestRestTemplate, @LocalServerPort val port: Int) {

    @Order(1)
    @Test
    @Throws(Exception::class)
    fun `should create account when input is valid`() {
        val input = AccountInput()
        input.birthday = LocalDate.now()
        input.email = "hello@gmail.com"
        input.firstName = "test"
        input.lastName = "hello"

        val uri = restTemplate.postForLocation(URI("http://localhost:$port/v1/accounts"), input)

        assertNotNull(uri)
    }

    @Order(2)
    @Test
    fun `should not found account when looking for not existing account`() {
        val response = restTemplate.getForEntity<ResponseData<Account>>("http://localhost:$port/v1/accounts/1")

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Order(3)
    @Test
    fun `should not retrieve any account`() {
        val response = restTemplate.getForEntity<ResponseData<List<Account>>>("http://localhost:$port/v1/accounts")

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }
}