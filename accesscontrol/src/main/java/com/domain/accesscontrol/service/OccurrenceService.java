package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.OccurenceDTO;
import com.domain.accesscontrol.entity.Occurrence;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.OccurrenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OccurrenceService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    public List<OccurenceDTO> getOccurrence(){

        return occurrenceRepository.findAll().stream().map(OccurenceDTO::new).collect(Collectors.toList());
    }

    public OccurenceDTO getOccurrenceById(Long id) {
        Optional<Occurrence> occurrence = occurrenceRepository.findById(id);
        return occurrence.map(OccurenceDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public OccurenceDTO insert(Occurrence occurrence) {
        Assert.isNull(occurrence.getId(),"Não foi possivel inserir o registro!");
        return new OccurenceDTO(occurrenceRepository.save(occurrence));
    }

    public OccurenceDTO update(Occurrence occurrence, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<Occurrence> optional = occurrenceRepository.findById(id);
        if(optional.isPresent()) {
            Occurrence db = optional.get();
            // Copia as propriedades
            db.setDescription(occurrence.getDescription());
            db.setName(occurrence.getName());
            db.setMovement(occurrence.getMovement());
            System.out.println("Ocorrência de id =" + db.getId());
            // Atualiza o nivel de acesso
            occurrenceRepository.save(db);
            return new OccurenceDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        occurrenceRepository.deleteById(id);

    }
}
