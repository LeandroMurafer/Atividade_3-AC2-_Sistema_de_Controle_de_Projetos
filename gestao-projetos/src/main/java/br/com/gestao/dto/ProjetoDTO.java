package br.com.gestao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjetoDTO(
        @NotBlank(message = "O nome do projeto é obrigatório")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
        String nome,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "A data de início é obrigatória")
        LocalDate dataInicio,

        LocalDate dataFim) {
}
