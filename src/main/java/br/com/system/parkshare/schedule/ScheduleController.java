package br.com.system.parkshare.schedule;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.system.parkshare.schedule.DTO.ScheduleDTO;
import br.com.system.parkshare.security.Token;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Schedule> create(JwtAuthenticationToken token, ScheduleDTO dto) throws Exception {

        return ResponseEntity.ok(service.create(Token.getidAccount(token), dto.getIdParking(), dto.getDay(),
                dto.getInit(), dto.getFinish()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable String id) {
        service.cancel(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

}