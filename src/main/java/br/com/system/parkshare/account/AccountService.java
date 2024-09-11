package br.com.system.parkshare.account;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.account.DTO.AccountDto;
import br.com.system.parkshare.account.DTO.LoginDto;
import br.com.system.parkshare.role.RoleRepository;
import br.com.system.parkshare.role.Role;
import br.com.system.parkshare.security.AuthDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AccountRepository getRepository() {
        return repository;
    }

    public ResponseEntity<Account> create(UUID idApp, Account user) {

        if (!repository.findByidAppAndEmail(idApp, user.getEmail()).isEmpty()) {
            throw new EntityNotFoundException("Usuário com e-mail " + user.getEmail() + " já cadastrado");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        user.setRoles(Set.of(roleRepository.findByName(Role.Values.ROLE_USER)));

        // user.setRoles(Set.of(roleRepository.findByName(Role.Values.USER.name())));

        return ResponseEntity.ok(repository.save(user));
    }

    public Account create(AccountDto dto) {

        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());

        return repository.save(account);
    }

    public ResponseEntity<String> login(LoginDto dto) {
        boolean exists = repository.existsByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (exists) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public ResponseEntity<Account> get(UUID id) {

        return ResponseEntity.ok(repository.findById(id).get());

    }

    public Account loadUserByEmailAndIdApp(UUID idApp, String email) throws EntityNotFoundException {

        final List<Account> user = repository.findByidAppAndEmail(idApp, email);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("Usuário com e-mail " + email + " não encontrado");
        }

        return user.get(0);

    }

    public Account loadUserByLoginAndIdApp(UUID idApp, String login) throws EntityNotFoundException {

        final List<Account> user = repository.findByidAppAndLogin(idApp, login);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("Usuário com Login " + login + " não encontrado");
        }

        return user.get(0);

    }

    public Account makeLogin(AuthDTO account, UUID idApp, String login)
            throws EntityNotFoundException {

        final List<Account> usuario = repository.findByidAppAndLogin(idApp, login);

        if (usuario.isEmpty() || !usuario.get(0).isLoginCorrect(account, passwordEncoder)) {
            throw new BadCredentialsException("Senha ou Login inválido");
        }

        return usuario.get(0);

    }
}
