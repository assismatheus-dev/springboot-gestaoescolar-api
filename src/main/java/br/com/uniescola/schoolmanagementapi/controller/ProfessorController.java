package br.com.uniescola.schoolmanagementapi.controller;

import br.com.uniescola.schoolmanagementapi.dto.request.ProfessorRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.ProfessorResponseDTO;
import br.com.uniescola.schoolmanagementapi.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> salvar(@RequestBody @Valid ProfessorRequestDTO dto) {
        ProfessorResponseDTO response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarTodos() {return ResponseEntity.ok(service.listarTodos());}

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {return ResponseEntity.ok(service.buscarporId(id));}

    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarParcial(@PathVariable Long id,
                                                 @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarParcial(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarTotal(@PathVariable Long id,
                                               @RequestBody @Valid ProfessorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarTotal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
