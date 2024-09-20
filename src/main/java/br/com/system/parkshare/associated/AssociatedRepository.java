package br.com.system.parkshare.associated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.system.parkshare.account.Account;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, UUID> {

    Optional<Associated> findByAccount(Account account);

}
