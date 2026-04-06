package br.com.uniescola.schoolmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String matricula;
    private Long turmaId;
}
