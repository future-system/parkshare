package br.com.system.parkshare.account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByEmailAndPassword(String email, String password);
    
    @Query("select a from account a where a.app.idApp = :idApp")//nao testado
    Optional<Account> findByidApp(@Param("idApp") UUID idApp);

    @Query("select a from account a where a.app.idApp = :idApp and a.idAccount = :idAccount")//nao testado
    Optional<Account> findByidAppAndIdAccount(@Param("idApp") UUID idApp, @Param("idAccount") UUID idAccount);
    
    @Query("select a from account a where a.app.idApp = :idApp and a.email = :email")//nao testado
    List<Account> findByidAppAndEmail(@Param("idApp") UUID idApp, @Param("email") String email);

    @Query("select a from account a where a.app.idApp = :idApp and a.login = :login")//nao testado
    List<Account> findByidAppAndLogin(@Param("idApp") UUID idApp, @Param("login") String login);

    //Jeito certo de fazer
    Page<Account> findAllByAppIdApp(UUID AppIdApp, Pageable  pageable);

}
