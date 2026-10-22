package br.com.gestao.repository;

import br.com.gestao.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    /** (a) Projeto com a lista de funcionários vinculados. */
    @Query("select p from Projeto p left join fetch p.funcionarios where p.id = :id")
    Optional<Projeto> buscarComFuncionarios(@Param("id") Integer id);

    /** (b) Projetos cujo início está dentro do intervalo informado (inclusive). */
    @Query("select p from Projeto p where p.dataInicio between :inicio and :fim order by p.dataInicio")
    List<Projeto> buscarPorPeriodoDeInicio(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    /** Listagem geral já com funcionários (usada pela tela de projetos). */
    @Query("select distinct p from Projeto p left join fetch p.funcionarios order by p.id")
    List<Projeto> listarTodosComFuncionarios();
}
