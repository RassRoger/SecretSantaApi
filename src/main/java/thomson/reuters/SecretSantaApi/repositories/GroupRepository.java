package thomson.reuters.SecretSantaApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thomson.reuters.SecretSantaApi.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
