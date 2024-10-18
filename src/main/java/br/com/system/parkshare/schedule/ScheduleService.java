package br.com.system.parkshare.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.account.AccountRepository;
import br.com.system.parkshare.associated.Associated;
import br.com.system.parkshare.client.Client;
import br.com.system.parkshare.client.ClientRepository;
import br.com.system.parkshare.parking.Parking;
import br.com.system.parkshare.parking.ParkingRepository;
import br.com.system.parkshare.scheduled.ScheduledRepository;
import br.com.system.parkshare.utils.Validation;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private Schedule schedule;

    public Schedule create(UUID idAccount, UUID idParking, LocalDate day, LocalDateTime init, LocalDateTime finish)
            throws Exception {

        Validation.validAccount(accountRepository, idAccount);

        Client client = clientRepository.findById(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Parking parking = parkingRepository.findById(idParking)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga não encontrada"));

        if (!repository.existsByidParkingAndDateVerificada(client.getIdClient(), init)) {
            throw new EntityNotFoundException("Data inicial invalida");
        }

        if (!repository.existsByidParkingAndDateVerificada(client.getIdClient(), finish)) {
            throw new EntityNotFoundException("Data final invalida");
        }

        schedule.setClient(client);
        schedule.setParking(parking);
        schedule.setDay(day);
        schedule.setInitDateTime(init);
        schedule.setFinishDateTime(finish);
        schedule.setStatus("E");// E = esperando aprovação do proprietario da vaga

        return repository.save(schedule);
    }

    public void cancel(UUID id) {
        repository.delete(findById(id));
    }

    public Schedule findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada"));
    }
}
