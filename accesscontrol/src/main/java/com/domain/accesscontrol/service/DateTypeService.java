package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.DateTypeDTO;
import com.domain.accesscontrol.entity.DateType;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.DateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DateTypeService {

    @Autowired
    private DateTypeRepository dateTypeRepository;

    public List<DateTypeDTO> getDateType(){

        return dateTypeRepository.findAll().stream().map(DateTypeDTO::new).collect(Collectors.toList());
    }

    public DateTypeDTO getDateTypeById(Long id) {
        Optional<DateType> dateType = dateTypeRepository.findById(id);
        return dateType.map(DateTypeDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public DateTypeDTO insert(DateType dateType) {
        Assert.isNull(dateType.getId(),"Não foi possivel inserir o registro!");
        return new DateTypeDTO(dateTypeRepository.save(dateType));
    }

    public DateTypeDTO update(DateType dateType, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<DateType> optional = dateTypeRepository.findById(id);
        if(optional.isPresent()) {
           DateType db = optional.get();
            // Copia as propriedades
            db.setDescription(dateType.getDescription());
            System.out.println("Tipo de Data de id = " + db.getId());
            // Atualiza o nivel de acesso
            dateTypeRepository.save(db);
            return new DateTypeDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        dateTypeRepository.deleteById(id);

    }
}
