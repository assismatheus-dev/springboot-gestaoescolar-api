package br.com.uniescola.schoolmanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TurmaResponseDTO {

    private Long id;
    private String nome;
    private Long professorId;
    private String professorNome;
}
