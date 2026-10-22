package br.com.gestao.dto;

import br.com.gestao.model.Funcionario;

public record FuncionarioResumoDTO(Integer id, String nome, String email) {

    public static FuncionarioResumoDTO de(Funcionario f) {
        return new FuncionarioResumoDTO(f.getId(), f.getNome(), f.getEmail());
    }
}
