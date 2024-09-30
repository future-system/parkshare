package br.com.system.parkshare.utils;

import java.util.UUID;

import br.com.system.parkshare.account.AccountRepository;
import jakarta.persistence.EntityNotFoundException;

public class Validation {
    
    public static void validAccount(AccountRepository repository, UUID idAccount) throws EntityNotFoundException{

        if(!repository.existsByIdAccountAndActiveIsTrue(idAccount)){
            throw new EntityNotFoundException("Usuario não encontrando");
        }

    }

}
