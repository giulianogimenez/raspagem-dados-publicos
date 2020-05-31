package com.giulianogimenez.scrappingview.evento.dto;

import com.giulianogimenez.scrappingview.ocorrencia.dto.OcorrenciaDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDTO implements Serializable {
    private String mes;
    private Integer ano;
    private List<OcorrenciaDTO> ocorrencias;
    private Integer quantidadeOcorrencias;
}
