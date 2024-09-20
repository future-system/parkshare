package br.com.system.parkshare.associated;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.account.AccountRepository;
import br.com.system.parkshare.account.AccountService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AssociatedService {

    @Autowired
    private AssociatedRepository associatedRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Associated create(UUID idAccount, String description) {
        Associated associated = new Associated();

        associated.setDescription(description);

        associated.setAccount(accountRepository.findById(idAccount)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada")));

        return associatedRepository.save(associated);
    }

    public void delete(UUID id) {
        associatedRepository.delete(findById(id));
    }

    public Associated findById(UUID id) {
        return associatedRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Associado não encontrado"));
    }

    public Associated findByIdAccount(UUID idAccount) {
        return associatedRepository.findByAccount(accountRepository.findById(idAccount).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"))).orElseThrow(() -> new EntityNotFoundException("Associado não encontrado"));
    }

    public Iterable<Associated> findAll() {
        return associatedRepository.findAll();
    }

    public Page<Associated> findAll(int page, int pageSize) {
        return associatedRepository.findAll(PageRequest.of(page, pageSize, Direction.DESC, "account.nickname"));
    }

    public Associated update(UUID id, String description) {
        Associated associated = findById(id);

        associated.setDescription(description);

        return associatedRepository.save(associated);
    }

}
