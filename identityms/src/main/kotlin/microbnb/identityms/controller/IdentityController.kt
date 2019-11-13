package microbnb.identityms.controller

import microbnb.identityms.model.dto.ResponseData
import microbnb.identityms.model.entity.Identity
import microbnb.identityms.service.IdentityService
import microbnb.identityms.model.dto.SubmitIdentity
import microbnb.identityms.exception.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@Validated
@RequestMapping("/v1/identities")
@RestController
class IdentityController(val identityService: IdentityService) {

    @PostMapping
    fun submitIdentity(identity: SubmitIdentity): ResponseEntity<URI> {
        var entity = Identity(0, identity.firstName, identity.lastName, identity.email, identity.birthday)
        var savedEntity = identityService.submitIdentity(entity)
        return ResponseEntity.created(URI.create("http://localhost:8081/v1/identities/${savedEntity.id}")).build()
    }
    
    @GetMapping
    fun allIdentity(): ResponseEntity<ResponseData<List<Identity>>> {
        return ResponseEntity.ok(ResponseData(identityService.allIdentity()))
    }
    
    @GetMapping("/{id}")
    fun getIdentity(@PathVariable id : Long): ResponseEntity<ResponseData<Identity>> {
        val identity: Optional<Identity> = identityService.getIdentity(id)

        if(identity.isPresent) {
            throw NotFoundException()
        }

        return ResponseEntity.notFound().build()
    }
}