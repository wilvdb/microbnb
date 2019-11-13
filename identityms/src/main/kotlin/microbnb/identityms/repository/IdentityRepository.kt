package microbnb.identityms.repository

import microbnb.identityms.model.entity.Identity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IdentityRepository : JpaRepository<Identity, Long> {
}