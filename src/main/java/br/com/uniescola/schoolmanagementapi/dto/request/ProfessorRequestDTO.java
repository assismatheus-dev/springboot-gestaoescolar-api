package br.com.uniescola.schoolmanagementapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Especialidade é obrigatória")
    private String especialidade;
}
