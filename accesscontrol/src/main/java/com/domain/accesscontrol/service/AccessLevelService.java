package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.AccessLevelDTO;
import com.domain.accesscontrol.entity.AccessLevel;
import com.domain.accesscontrol.repository.AccessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessLevelService {

    @Autowired
    private AccessLevelRepository accessLevelRepository;

    public List<AccessLevelDTO> getAccessLevel(){

        return accessLevelRepository.findAll().stream().map(AccessLevelDTO::new).collect(Collectors.toList());
    }

    public Optional<AccessLevelDTO> getAccessLevelById(Long id) {
        return accessLevelRepository.findById(id).map(AccessLevelDTO::new);
    }

    public AccessLevelDTO insert(AccessLevel accessLevel) {
        Assert.isNull(accessLevel.getId(),"Não foi possivel inserir o registro!");
        return new AccessLevelDTO(accessLevelRepository.save(accessLevel));
    }

    public AccessLevelDTO update(AccessLevel accessLevel, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o nivel de acesso!");
        //Busca o registro no DB
        Optional<AccessLevel> optional = accessLevelRepository.findById(id);
        if(optional.isPresent()) {
            AccessLevel db = optional.get();
            // Copia as propriedades
            db.setDescription(accessLevel.getDescription());
            System.out.println("Nivel de Acesso id" + db.getId());
            // Atualiza o nivel de acesso
            accessLevelRepository.save(db);
            return new AccessLevelDTO(db);
        }else {
            return null;
        }
    }

    public boolean delete(Long id) {

        if(getAccessLevelById(id).isPresent()){
            accessLevelRepository.deleteAllById(id);
            return true;
        }
        return false;
    }
}
