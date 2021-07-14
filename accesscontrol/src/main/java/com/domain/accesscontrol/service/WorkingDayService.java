package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.WorkingDayDTO;
import com.domain.accesscontrol.entity.WorkingDay;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.WorkingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkingDayService {

    @Autowired
    private WorkingDayRepository workingDayRepository;

    public List<WorkingDayDTO> getWorkingDay(){

        return workingDayRepository.findAll().stream().map(WorkingDayDTO::new).collect(Collectors.toList());
    }

    public WorkingDayDTO getWorkingDayById(Long id) {
        Optional<WorkingDay> workingDay = workingDayRepository.findById(id);
        return workingDay.map(WorkingDayDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public WorkingDayDTO insert(WorkingDay workingDay) {
        Assert.isNull(workingDay.getId(),"Não foi possivel inserir o registro!");
        return new WorkingDayDTO(workingDayRepository.save(workingDay));
    }

    public WorkingDayDTO update(WorkingDay workingDay, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<WorkingDay> optional = workingDayRepository.findById(id);
        if(optional.isPresent()) {
            WorkingDay db = optional.get();
            // Copia as propriedades
            db.setDescription(workingDay.getDescription());
            System.out.println("Nivel de Acesso id" + db.getId());
            // Atualiza o nivel de acesso
            workingDayRepository.save(db);
            return new WorkingDayDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        workingDayRepository.deleteById(id);

    }
}
