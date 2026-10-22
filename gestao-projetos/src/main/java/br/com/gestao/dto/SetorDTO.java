package br.com.gestao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetorDTO(
        @NotBlank(message = "O nome do setor é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome) {
}
