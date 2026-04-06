package br.com.uniescola.schoolmanagementapi.service;

import br.com.uniescola.schoolmanagementapi.database.entity.Professor;
import br.com.uniescola.schoolmanagementapi.database.entity.Turma;
import br.com.uniescola.schoolmanagementapi.database.repository.ProfessorRepository;
import br.com.uniescola.schoolmanagementapi.database.repository.TurmaRepository;
import br.com.uniescola.schoolmanagementapi.dto.request.TurmaRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.TurmaResponseDTO;
import br.com.uniescola.schoolmanagementapi.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public TurmaResponseDTO salvar(TurmaRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + dto.getProfessorId()));
        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setProfessor(professor);

        Turma turmaSalva = turmaRepository.save(turma);
        return toResponseDTO(turmaSalva);
    }

    public List<TurmaResponseDTO> listarTodos() {
        return turmaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TurmaResponseDTO buscarPorId(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com o id: " + id));
        return toResponseDTO(turma);
    }

    @Transactional
    public TurmaResponseDTO atualizarTotal(Long id, TurmaRequestDTO dto) {
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com o id: " + id));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + dto.getProfessorId()));

        turmaExistente.setNome(dto.getNome());
        turmaExistente.setProfessor(professor);

        turmaRepository.save(turmaExistente);
        return toResponseDTO(turmaExistente);
    }

    @Transactional
    public TurmaResponseDTO atualizarParcial(Long id, TurmaRequestDTO dto) {
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com o id: " + id));

        if (dto.getNome() != null) { turmaExistente.setNome(dto.getNome()); }

        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + dto.getProfessorId()));
            turmaExistente.setProfessor(professor);

            turmaExistente.setProfessor(professor);
        }

        turmaRepository.save(turmaExistente);
        return toResponseDTO(turmaExistente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turma não encontrada com o id: " + id);
        }
        turmaRepository.deleteById(id);
    }

    private TurmaResponseDTO toResponseDTO(Turma turma) {
        return new TurmaResponseDTO(
                turma.getId(),
                turma.getNome(),
                turma.getProfessor() != null ? turma.getProfessor().getId() : null,
                turma.getProfessor() != null ? turma.getProfessor().getNome() : null
        );
    }
}
