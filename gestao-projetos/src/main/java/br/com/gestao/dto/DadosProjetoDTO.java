package br.com.gestao.dto;

import br.com.gestao.model.Projeto;

import java.time.LocalDate;
import java.util.List;

public record DadosProjetoDTO(
        Integer id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<FuncionarioResumoDTO> funcionarios) {

    public static DadosProjetoDTO de(Projeto p) {
        return new DadosProjetoDTO(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getDataInicio(),
                p.getDataFim(),
                p.getFuncionarios().stream().map(FuncionarioResumoDTO::de).toList());
    }
}
