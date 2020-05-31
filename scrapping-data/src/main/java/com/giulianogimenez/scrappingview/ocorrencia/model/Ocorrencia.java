package com.giulianogimenez.scrappingview.ocorrencia.model;

import com.giulianogimenez.scrappingview.evento.model.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name="ocorrencia")
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Integer quantidade;

    @ManyToOne
    private Evento evento;
}
