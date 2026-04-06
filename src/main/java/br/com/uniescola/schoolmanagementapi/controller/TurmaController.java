package br.com.uniescola.schoolmanagementapi.controller;

import br.com.uniescola.schoolmanagementapi.dto.request.TurmaRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.TurmaResponseDTO;
import br.com.uniescola.schoolmanagementapi.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/turmas")
public class TurmaController {
    private final TurmaService service;

    public TurmaController(TurmaService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> salvar(@RequestBody @Valid TurmaRequestDTO dto) {
        TurmaResponseDTO response = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTodos() {return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(@PathVariable Long id) {return ResponseEntity.ok(service.buscarPorId(id));}

    @PatchMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizarParcial(@PathVariable Long id,
                                             @RequestBody TurmaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarParcial(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizarTotal(@PathVariable Long id,
                                           @RequestBody @Valid TurmaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarTotal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
