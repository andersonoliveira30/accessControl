package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.UserDTO;
import com.domain.accesscontrol.entity.User;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUser(){

        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(),"Não foi possivel inserir o registro!");
        return new UserDTO(userRepository.save(user));
    }

    public UserDTO update(User user, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            // Copia as propriedades
            db.setName(user.getName());
            db.setCompany(user.getCompany());
            db.setTolerance(user.getTolerance());
            db.setStartjourney(user.getStartjourney());
            db.setJourneyend(user.getJourneyend());
            db.setAccessLevel(user.getAccessLevel());
            db.setWorkingDay(user.getWorkingDay());
            db.setUserCategory(user.getUserCategory());
            System.out.println("Nivel de Acesso id" + db.getId());
            // Atualiza o nivel de acesso
            userRepository.save(db);
            return new UserDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        userRepository.deleteById(id);

    }
}
