package br.com.uniescola.schoolmanagementapi.service;

import br.com.uniescola.schoolmanagementapi.database.entity.Professor;
import br.com.uniescola.schoolmanagementapi.database.repository.ProfessorRepository;
import br.com.uniescola.schoolmanagementapi.dto.request.ProfessorRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.ProfessorResponseDTO;
import br.com.uniescola.schoolmanagementapi.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public ProfessorResponseDTO salvar(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setEspecialidade(dto.getEspecialidade());

        Professor professorSalvo = professorRepository.save(professor);
        return toResponseDTO(professorSalvo);
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));
        return toResponseDTO(professor);
    }

    @Transactional
    public ProfessorResponseDTO atualizarTotal(Long id, ProfessorRequestDTO dto) {
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));

        professorExistente.setNome(dto.getNome());
        professorExistente.setEmail(dto.getEmail());
        professorExistente.setEspecialidade(dto.getEspecialidade());

        professorRepository.save(professorExistente);
        return toResponseDTO(professorExistente);
    }

    @Transactional
    public ProfessorResponseDTO atualizarParcial(Long id, ProfessorRequestDTO dto) {
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));

        if (dto.getNome() != null) { professorExistente.setNome(dto.getNome()); }
        if (dto.getEmail() != null) { professorExistente.setEmail(dto.getEmail()); }
        if (dto.getEspecialidade() != null) { professorExistente.setEspecialidade(dto.getEspecialidade()); }

        professorRepository.save(professorExistente);
        return toResponseDTO(professorExistente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor não encontrado com o id: " + id);
        }
        professorRepository.deleteById(id);
    }

    private ProfessorResponseDTO toResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getEspecialidade()
        );
    }
}
