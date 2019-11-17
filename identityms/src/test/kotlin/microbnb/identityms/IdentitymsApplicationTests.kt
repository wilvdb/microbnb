package microbnb.identityms

import microbnb.identityms.model.dto.ResponseData
import microbnb.identityms.model.dto.SubmitIdentity
import microbnb.identityms.model.entity.Identity
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
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URI
import java.time.LocalDate

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mock")
class IdentitymsApplicationTests(@Autowired val restTemplate: TestRestTemplate, @LocalServerPort val port: Int) {

	@Order(1)
	@Test
	@Throws(Exception::class)
	fun `should create account when input is valid`() {
		val input = SubmitIdentity("test", "hello", "hello@gmail.com", LocalDate.now())

		val uri = restTemplate.postForLocation(URI("http://localhost:$port/v1/identities"), input)

		assertNotNull(uri)
	}

	@Order(2)
	@Test
	fun `should not found account when looking for not existing account`() {
		val response = restTemplate.getForEntity<ResponseData<Identity>>("http://localhost:$port/v1/identities/1")

		assertEquals(HttpStatus.OK, response.statusCode)
	}

	@Order(3)
	@Test
	fun `should retrieve all account`() {
		val response = restTemplate.getForEntity<ResponseData<List<Identity>>>("http://localhost:$port/v1/identities")

		assertEquals(HttpStatus.OK, response.statusCode)
		assertEquals(response.body?.data?.size, 1)
	}
}
