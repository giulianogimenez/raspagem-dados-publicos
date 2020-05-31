package com.giulianogimenez.scrappingview.ocorrencia.converter;

import com.giulianogimenez.scrappingview.ocorrencia.dto.OcorrenciaDTO;
import com.giulianogimenez.scrappingview.ocorrencia.model.Ocorrencia;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaConverter {
    public Ocorrencia toModel(OcorrenciaDTO ocorrenciaDTO) {
        return Ocorrencia.builder()
                .descricao(ocorrenciaDTO.getDescricao())
                .quantidade(ocorrenciaDTO.getQuantidade())
                .build();
    }

    public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        return OcorrenciaDTO.builder()
                .descricao(ocorrencia.getDescricao())
                .quantidade(ocorrencia.getQuantidade())
                .build();
    }


}
