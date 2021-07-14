package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.UserCategoryDTO;
import com.domain.accesscontrol.entity.UserCategory;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public List<UserCategoryDTO> getUserCategory(){

        return userCategoryRepository.findAll().stream().map(UserCategoryDTO::new).collect(Collectors.toList());
    }

    public UserCategoryDTO getUserCategoryById(Long id) {
        Optional<UserCategory> userCategory = userCategoryRepository.findById(id);
        return userCategory.map(UserCategoryDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public UserCategoryDTO insert(UserCategory userCategory) {
        Assert.isNull(userCategory.getId(),"Não foi possivel inserir o registro!");
        return new UserCategoryDTO(userCategoryRepository.save(userCategory));
    }

    public UserCategoryDTO update(UserCategory userCategory, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<UserCategory> optional = userCategoryRepository.findById(id);
        if(optional.isPresent()) {
            UserCategory db = optional.get();
            // Copia as propriedades
            db.setDescription(userCategory.getDescription());
            System.out.println("Nivel de Acesso id" + db.getId());
            // Atualiza o nivel de acesso
            userCategoryRepository.save(db);
            return new UserCategoryDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        userCategoryRepository.deleteById(id);

    }
}
