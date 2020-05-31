package com.giulianogimenez.scrappingview.evento.controller;

import com.giulianogimenez.scrappingview.evento.converter.EventoConverter;
import com.giulianogimenez.scrappingview.evento.dto.EventoDTO;
import com.giulianogimenez.scrappingview.evento.model.Evento;
import com.giulianogimenez.scrappingview.evento.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService service;

    @Autowired
    private EventoConverter eventoConverter;

    @PostMapping
    public EventoDTO save(@RequestBody EventoDTO eventoDTO) {
        Evento evento = eventoConverter.toModel(eventoDTO);
        return eventoConverter.toDTO(service.save(evento));
    }

    @GetMapping("/ordernar-por-media-violencia")
    public List<EventoDTO> ordernarPorMediaDaViolencia() {
        return eventoConverter.toDTO(service.ordernarPorMediaDaViolencia());
    }

    @GetMapping("/anos")
    public List<Integer> anos() {
        return service.listarAnos();
    }

    @GetMapping("/{mes}/{ano}")
    public List<EventoDTO> findAllByMesEAno(@PathVariable("mes") String mes, @PathVariable("ano") Integer ano){
        return eventoConverter.toDTO(service.findAllByMesEAno(mes, ano));
    }
}
