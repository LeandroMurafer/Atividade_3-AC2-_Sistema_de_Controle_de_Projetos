package br.com.gestao.repository;

import br.com.gestao.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    /** (d) Todos os setores com seus funcionários. */
    @Query("select distinct s from Setor s left join fetch s.funcionarios order by s.id")
    List<Setor> listarComFuncionarios();

    @Query("select s from Setor s left join fetch s.funcionarios where s.id = :id")
    Optional<Setor> buscarComFuncionarios(@Param("id") Integer id);

    boolean existsByNomeIgnoreCase(String nome);
}
