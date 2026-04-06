package br.com.uniescola.schoolmanagementapi.service;

import br.com.uniescola.schoolmanagementapi.database.entity.Aluno;
import br.com.uniescola.schoolmanagementapi.database.entity.Turma;
import br.com.uniescola.schoolmanagementapi.database.repository.AlunoRepository;
import br.com.uniescola.schoolmanagementapi.database.repository.TurmaRepository;
import br.com.uniescola.schoolmanagementapi.dto.request.AlunoRequestDTO;
import br.com.uniescola.schoolmanagementapi.dto.response.AlunoResponseDTO;
import br.com.uniescola.schoolmanagementapi.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setDataDeNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com o id: " + dto.getTurmaId()));
            aluno.setTurma(turma);
        }

        Aluno alunoSalvo = alunoRepository.save(aluno);
        return toResponseDTO(alunoSalvo);
    }

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));
        return toResponseDTO(aluno);
    }

    @Transactional
    public AlunoResponseDTO atualizarTotal(Long id, AlunoRequestDTO dto) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setDataDeNascimento(dto.getDataNascimento());
        alunoExistente.setMatricula(dto.getMatricula());

        Aluno alunoAtualizado = alunoRepository.save(alunoExistente);
        return toResponseDTO(alunoAtualizado);
    }

    @Transactional
    public AlunoResponseDTO atualizarParcial(Long id, AlunoRequestDTO dto) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));

        if (dto.getNome() != null) { alunoExistente.setNome(dto.getNome()); }
        if (dto.getEmail() != null) { alunoExistente.setEmail(dto.getEmail()); }
        if (dto.getDataNascimento() != null) { alunoExistente.setDataDeNascimento(dto.getDataNascimento()); }
        if (dto.getMatricula() != null) { alunoExistente.setMatricula(dto.getMatricula()); }

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com o id: " + dto.getTurmaId()));
            alunoExistente.setTurma(turma);
        }

        Aluno alunoAtualizado = alunoRepository.save(alunoExistente);
        return toResponseDTO(alunoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado com o id: " + id);
        }
        alunoRepository.deleteById(id);
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getDataDeNascimento(),
                aluno.getMatricula(),
                aluno.getTurma() != null ? aluno.getTurma().getId() : null
        );
    }
}
