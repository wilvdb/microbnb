package microbnb.identityms.service

import microbnb.identityms.model.entity.Identity
import microbnb.identityms.repository.IdentityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class IdentityService(val identityRepository: IdentityRepository) {

    @Transactional
    fun submitIdentity(identity: Identity): Identity {
        return identityRepository.save(identity)
    }

    @Transactional(readOnly = true)
    fun allIdentity(): List<Identity> {
        return identityRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getIdentity(id: Long): Optional<Identity> {
        return identityRepository.findById(id)
    }
}