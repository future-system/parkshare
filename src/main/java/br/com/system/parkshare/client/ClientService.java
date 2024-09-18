package br.com.system.parkshare.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.account.Account;
import br.com.system.parkshare.account.AccountRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Client createClient(UUID accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));
        
        Client client = new Client();
        client.setAccount(account);
        return clientRepository.save(client);
    }
    
}
