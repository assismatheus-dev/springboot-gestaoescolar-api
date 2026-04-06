package br.com.uniescola.schoolmanagementapi.database.repository;

import br.com.uniescola.schoolmanagementapi.database.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Optional<Turma> findByNome(String nome);

    List<Turma> findByProfessorId(Long professorId);
}
