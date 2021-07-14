package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.AccessLevelDTO;
import com.domain.accesscontrol.dto.CalendarDTO;
import com.domain.accesscontrol.entity.AccessLevel;
import com.domain.accesscontrol.entity.Calendar;
import com.domain.accesscontrol.service.AccessLevelService;
import com.domain.accesscontrol.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping()
    public ResponseEntity<List<CalendarDTO>> get(){
        return ResponseEntity.ok(calendarService.getCalendar());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdCalendar(@PathVariable("id") Long id){
        CalendarDTO calendarDTO = calendarService.getCalendarById(id);
        return ResponseEntity.ok(calendarDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Calendar calendar){

        CalendarDTO ca = calendarService.insert(calendar);
        URI location = getUri(ca.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Calendar calendar){
        calendar.setId(id);
        CalendarDTO ca = calendarService.update(calendar, id);
        return ca != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
       calendarService.delete(id);
        return ResponseEntity.ok().build();

    }

}
