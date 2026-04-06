package br.com.uniescola.schoolmanagementapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class TurmaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Id do professor é obrigatório")
    private Long professorId;
}
