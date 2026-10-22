package br.com.gestao.dto;

import br.com.gestao.model.Setor;

import java.util.List;

public record DadosSetorDTO(Integer id, String nome, List<FuncionarioResumoDTO> funcionarios) {

    public static DadosSetorDTO de(Setor s) {
        return new DadosSetorDTO(
                s.getId(),
                s.getNome(),
                s.getFuncionarios().stream().map(FuncionarioResumoDTO::de).toList());
    }
}
