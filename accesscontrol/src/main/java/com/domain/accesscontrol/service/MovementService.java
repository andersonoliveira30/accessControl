package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.AccessLevelDTO;
import com.domain.accesscontrol.dto.MovementDTO;
import com.domain.accesscontrol.entity.AccessLevel;
import com.domain.accesscontrol.entity.Movement;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.AccessLevelRepository;
import com.domain.accesscontrol.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    public List<MovementDTO> getMovement(){

        return movementRepository.findAll().stream().map(MovementDTO::new).collect(Collectors.toList());
    }

    public MovementDTO getMovementById(Long id) {
        Optional<Movement> movement = movementRepository.findById(id);
        return movement.map(MovementDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public MovementDTO insert(Movement movement) {
        Assert.isNull(movement.getId(),"Não foi possivel inserir o registro!");
        return new MovementDTO(movementRepository.save(movement));
    }

    public MovementDTO update(Movement movement, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<Movement> optional = movementRepository.findById(id);
        if(optional.isPresent()) {
           Movement db = optional.get();
            // Copia as propriedades
            db.setDateEntry(movement.getDateEntry());
            db.setCalendar(movement.getCalendar());
            db.setDepartureDate(movement.getDepartureDate());
            db.setOccurrence(movement.getOccurrence());
            db.setTimeCourse(movement.getTimeCourse());
            db.setOccurrence(movement.getOccurrence());
            System.out.println("Nivel de Acesso id" + db.getId());
            // Atualiza o nivel de acesso
            movementRepository.save(db);
            return new MovementDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        movementRepository.deleteById(id);

    }
}
