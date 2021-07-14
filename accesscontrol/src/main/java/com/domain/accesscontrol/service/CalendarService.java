package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.CalendarDTO;
import com.domain.accesscontrol.entity.Calendar;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public List<CalendarDTO> getCalendar(){

        return calendarRepository.findAll().stream().map(CalendarDTO::new).collect(Collectors.toList());
    }

    public CalendarDTO getCalendarById(Long id) {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        return calendar.map(CalendarDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public CalendarDTO insert(Calendar calendar) {
        Assert.isNull(calendar.getId(),"Não foi possivel inserir o registro!");
        return new CalendarDTO(calendarRepository.save(calendar));
    }

    public CalendarDTO update(Calendar calendar, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o regsitro!");
        //Busca o registro no DB
        Optional<Calendar> optional = calendarRepository.findById(id);
        if(optional.isPresent()) {
           Calendar db = optional.get();
            // Copia as propriedades
            db.setDescription(calendar.getDescription());
            db.setSpecialDate(calendar.getSpecialDate());
            db.setDateType(calendar.getDateType());
            System.out.println("Calendário de id = " + db.getId());
            // Atualiza o nivel de acesso
            calendarRepository.save(db);
            return new CalendarDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        calendarRepository.deleteById(id);

    }
}
