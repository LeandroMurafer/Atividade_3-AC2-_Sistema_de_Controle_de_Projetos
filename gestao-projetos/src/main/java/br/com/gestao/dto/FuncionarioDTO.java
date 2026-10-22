package br.com.gestao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        @NotBlank(message = "O nome do funcionário é obrigatório")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotNull(message = "O setor é obrigatório")
        Integer idSetor) {
}
