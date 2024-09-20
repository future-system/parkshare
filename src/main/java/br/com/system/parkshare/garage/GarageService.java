package br.com.system.parkshare.garage;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.associated.AssociatedService;

@Service
public class GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private AssociatedService associatedService;

    public Garage create(UUID idAccount, String name, float price, String street, String block, int number, String state,
            String description, double latitude, double longitude) {
        final Garage garage = new Garage();

        garage.setName(name);
        garage.setPrice(price);
        garage.setStreet(street);
        garage.setBlock(block);
        garage.setNumber(number);
        garage.setState(state);
        garage.setDescription(description);
        garage.setLatitude(latitude);
        garage.setLongitude(longitude);
        garage.setAssociated(associatedService.findByIdAccount(idAccount));

        return garageRepository.save(garage);
    }

    public void delete(UUID id) {
        garageRepository.delete(findById(id));
    }

    public Garage findById(UUID id) {
        return garageRepository.findById(id).orElseThrow(() -> new RuntimeException("Associado não encontrado"));
    }

    public Iterable<Garage> findAll() {
        return garageRepository.findAll();
    }

    public Page<Garage> findAll(int page, int pageSize) {
        return garageRepository.findAll(PageRequest.of(page, pageSize, Direction.DESC, "associated.account.nickname"));
    }

    public Garage update(UUID id, String name, float price, String street, String block, int number, String state,
    String description, double latitude, double longitude) {
        final Garage garage = findById(id);

        garage.setName(name);
        garage.setPrice(price);
        garage.setStreet(street);
        garage.setBlock(block);
        garage.setNumber(number);
        garage.setState(state);
        garage.setDescription(description);
        garage.setLatitude(latitude);
        garage.setLongitude(longitude);

        return garageRepository.save(garage);
    }

}
