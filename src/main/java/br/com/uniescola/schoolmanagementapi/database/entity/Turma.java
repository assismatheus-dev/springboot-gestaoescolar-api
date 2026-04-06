package br.com.uniescola.schoolmanagementapi.database.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @ToString.Exclude
    private Professor professor;

    @OneToMany(mappedBy = "turma")
    @ToString.Exclude
    private List<Aluno> alunos;
}
