package br.com.uniescola.schoolmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfessorResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String especialidade;
}
