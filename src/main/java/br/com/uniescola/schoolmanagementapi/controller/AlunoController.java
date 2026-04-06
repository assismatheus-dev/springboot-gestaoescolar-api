package br.com.uniescola.schoolmanagementapi.controller;

import br.com.uniescola.schoolmanagementapi.dto.request.AlunoRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.AlunoResponseDTO;
import br.com.uniescola.schoolmanagementapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> salvar(@RequestBody @Valid AlunoRequestDTO dto) {
        AlunoResponseDTO response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {return ResponseEntity.ok(service.listarTodos());}

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {return ResponseEntity.ok(service.buscarporid(id));}

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarParcial(@PathVariable Long id,
                                             @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarParcial(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarTotal(@PathVariable Long id,
                                           @RequestBody @Valid AlunoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarTotal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
