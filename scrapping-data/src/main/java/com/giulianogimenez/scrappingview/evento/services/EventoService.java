package com.giulianogimenez.scrappingview.evento.services;

import com.giulianogimenez.scrappingview.evento.dto.EventoDTO;
import com.giulianogimenez.scrappingview.evento.model.Evento;
import com.giulianogimenez.scrappingview.evento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento save(Evento evento){
        if(evento.getMes().isEmpty()) {
            throw new RuntimeException("O mês não foi informado.");
        }
        if(evento.getAno() == null ||
          evento.getAno() < 2011 || evento.getAno() > LocalDate.now().getYear()) {
            throw new RuntimeException("O ano está incorreto.");
        }
        if(evento.getOcorrencias() == null) {
            throw new RuntimeException("Ocorrencia não informada.");
        }
        evento.getOcorrencias().forEach(ocorrencia -> {
            ocorrencia.setEvento(evento);
        });
        if(repository.countByMesEAno(evento.getMes(), evento.getAno()) > 0l) {
            throw new RuntimeException("Evento já cadastrado.");
        }
        return repository.save(evento);
    }

    public List<Evento> ordernarPorMediaDaViolencia() {
        return repository.ordernarPorMediaDaViolencia();
    }

    public List<Integer> listarAnos() {
        return repository.findAllAnos();
    }

    public List<Evento> findAllByMesEAno(String mes, Integer ano) {
        return repository.findAllByMesAndAno(mes, ano);
    }
}
