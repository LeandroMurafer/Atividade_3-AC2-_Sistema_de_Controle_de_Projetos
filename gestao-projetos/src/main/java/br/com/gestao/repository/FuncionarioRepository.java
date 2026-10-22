package br.com.gestao.repository;

import br.com.gestao.model.Funcionario;
import br.com.gestao.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    /** (c) Todos os projetos aos quais o funcionário está vinculado. */
    @Query("select distinct p from Projeto p join p.funcionarios f left join fetch p.funcionarios "
            + "where f.id = :idFuncionario order by p.id")
    List<Projeto> buscarProjetosDoFuncionario(@Param("idFuncionario") Integer idFuncionario);

    boolean existsByEmailIgnoreCase(String email);
}
