package br.com.system.parkshare.associated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, UUID> {

}
