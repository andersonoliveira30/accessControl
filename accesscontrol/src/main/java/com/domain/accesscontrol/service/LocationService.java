package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.LocationDTO;
import com.domain.accesscontrol.entity.Location;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<LocationDTO> getLocation(){

        return locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    public LocationDTO getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(LocationDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public LocationDTO insert(Location location) {
        Assert.isNull(location.getId(),"Não foi possivel inserir o registro!");
        return new LocationDTO(locationRepository.save(location));
    }

    public LocationDTO update(Location location, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<Location> optional = locationRepository.findById(id);
        if(optional.isPresent()) {
            Location db = optional.get();
            // Copia as propriedades
            db.setDescription(location.getDescription());
            System.out.println("Localização de id = " + db.getId());
            // Atualiza o nivel de acesso
            locationRepository.save(db);
            return new LocationDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

       locationRepository.deleteById(id);
    }
}
