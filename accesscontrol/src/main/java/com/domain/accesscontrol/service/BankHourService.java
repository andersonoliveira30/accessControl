package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.BankHourDTO;
import com.domain.accesscontrol.entity.BankHour;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.BankHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankHourService {

    @Autowired
    private BankHourRepository bankHourRepository;

    public List<BankHourDTO> getBankHour(){

        return bankHourRepository.findAll().stream().map(BankHourDTO::new).collect(Collectors.toList());
    }

    public BankHourDTO getBankHourById(Long id) {
        Optional<BankHour> bankHour = bankHourRepository.findById(id);
        return bankHour.map(BankHourDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public BankHourDTO insert(BankHour bankHour) {
        Assert.isNull(bankHour.getId(),"Não foi possivel inserir o registro!");
        return new BankHourDTO(bankHourRepository.save(bankHour));
    }

    public BankHourDTO update(BankHour bankHour, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<BankHour> optional = bankHourRepository.findById(id);
        if(optional.isPresent()) {
            BankHour db = optional.get();
            // Copia as propriedades
            db.setAmountHours(bankHour.getAmountHours());
            db.setHoursBalance(bankHour.getHoursBalance());
            db.setDateWorked(bankHour.getDateWorked());
            System.out.println("Banco de Horas de Id-" + db.getId());
            // Atualiza o nivel de acesso
            bankHourRepository.save(db);
            return new BankHourDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        bankHourRepository.deleteById(id);

    }
}
