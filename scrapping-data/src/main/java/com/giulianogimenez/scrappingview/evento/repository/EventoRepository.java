package com.giulianogimenez.scrappingview.evento.repository;

import com.giulianogimenez.scrappingview.evento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query("SELECT COUNT(e) from Evento e WHERE e.mes = :mes AND e.ano = :ano")
    Long countByMesEAno(@Param("mes") String mes, @Param("ano") Integer ano);

    @Query("SELECT e from Evento e JOIN e.ocorrencias o group by e.id order by sum(o.quantidade) desc")
    List<Evento> ordernarPorMediaDaViolencia();

    @Query("SELECT distinct e.ano FROM Evento e order by e.ano desc")
    List<Integer> findAllAnos();

    List<Evento> findAllByMesAndAno(String mes, Integer ano);
}
