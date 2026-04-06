package br.com.uniescola.schoolmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProfessorResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String especialidade;
}
