package microbnb.identityms.controller

import microbnb.identityms.exception.NotFoundException
import microbnb.identityms.model.dto.ResponseData
import microbnb.identityms.model.dto.SubmitIdentity
import microbnb.identityms.model.entity.Identity
import microbnb.identityms.service.IdentityService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@Validated
@RequestMapping("/v1/identities", produces = ["application/json"])
@RestController
class IdentityController(val identityService: IdentityService) {

    @PostMapping(consumes = ["application/json"])
    fun submitIdentity(@Valid @RequestBody identity: SubmitIdentity): ResponseEntity<URI> {
        var entity = Identity(0, identity.firstName, identity.lastName, identity.email, identity.birthday)
        var savedEntity = identityService.submitIdentity(entity)
        return ResponseEntity.created(URI.create("http://localhost:8081/v1/identities/${savedEntity.id}")).build()
    }
    
    @GetMapping
    fun allIdentity(): ResponseEntity<ResponseData<List<Identity>>> {
        val identities = identityService.allIdentity()
        if(identities.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        return ResponseEntity.ok(ResponseData(identities))
    }
    
    @GetMapping("/{id}")
    fun getIdentity(@PathVariable id : Long): ResponseEntity<ResponseData<Identity>> {
        val identity: Optional<Identity> = identityService.getIdentity(id)

        return ResponseEntity.ok(ResponseData(identity.orElseThrow{ NotFoundException() }))
    }
}