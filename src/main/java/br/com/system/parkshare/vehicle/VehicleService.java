package br.com.system.parkshare.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.client.Client;
import br.com.system.parkshare.client.ClientRepository;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Vehicle> createVehicle(UUID idAccount, List<Vehicle> vehicles) {

        Client client = clientRepository.findByAccountIdAccount(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        for (Vehicle vehicle : vehicles) {
            vehicle.setClient(client);
        }

        return repository.saveAll(vehicles);
    }

    public List<Vehicle> getVehicles(UUID idAccount) {
        
        Client client = clientRepository.findByAccountIdAccount(idAccount)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        return repository.findByClientIdClient(client.getIdClient());

    }

}
