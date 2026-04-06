package br.com.uniescola.schoolmanagementapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AlunoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email não é válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    private Long turmaId;
}
