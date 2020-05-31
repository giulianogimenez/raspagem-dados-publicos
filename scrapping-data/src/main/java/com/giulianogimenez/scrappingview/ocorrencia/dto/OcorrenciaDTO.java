package com.giulianogimenez.scrappingview.ocorrencia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OcorrenciaDTO {
    private String descricao;
    private Integer quantidade;
}
