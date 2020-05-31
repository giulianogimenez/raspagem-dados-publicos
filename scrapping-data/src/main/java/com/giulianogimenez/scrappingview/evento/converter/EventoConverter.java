package com.giulianogimenez.scrappingview.evento.converter;

import com.giulianogimenez.scrappingview.evento.dto.EventoDTO;
import com.giulianogimenez.scrappingview.evento.model.Evento;
import com.giulianogimenez.scrappingview.ocorrencia.converter.OcorrenciaConverter;
import com.giulianogimenez.scrappingview.ocorrencia.dto.OcorrenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoConverter {
    @Autowired
    private OcorrenciaConverter ocorrenciaConverter;

    public Evento toModel(EventoDTO eventoDTO){
        Evento evento = Evento.builder()
                .ano(eventoDTO.getAno())
                .mes(eventoDTO.getMes())
                .build();

        evento.setOcorrencias(eventoDTO.getOcorrencias().stream().map(ocorrenciaDTO -> {
            return ocorrenciaConverter.toModel(ocorrenciaDTO);
        }).collect(Collectors.toList()));
        return evento;
    }

    public EventoDTO toDTO(Evento evento){
        EventoDTO eventoDTO = EventoDTO.builder()
                .ano(evento.getAno())
                .mes(evento.getMes())
                .build();

        eventoDTO.setOcorrencias(evento.getOcorrencias().stream().map(ocorrencia -> {
            return ocorrenciaConverter.toDTO(ocorrencia);
        }).collect(Collectors.toList()));
        eventoDTO.setQuantidadeOcorrencias(eventoDTO.getOcorrencias().stream()
                .map(OcorrenciaDTO::getQuantidade)
                .collect(Collectors.toList())
                .stream().reduce(0, Integer::sum));
        return eventoDTO;
    }

    public List<EventoDTO> toDTO(List<Evento> eventos){
        return eventos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
