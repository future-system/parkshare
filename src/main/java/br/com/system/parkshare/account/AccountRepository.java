package br.com.system.parkshare.account;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByEmailAndPassword(String email, String password);
    
    Optional<Account> findByEmail(String email);

    boolean existsByIdAccountAndActiveIsTrue(UUID idAccount);

}
