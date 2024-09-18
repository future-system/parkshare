package br.com.system.parkshare.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository<Client, UUID> {

    Optional<Client> findByAccountIdAccount(UUID idAcoount);

}
