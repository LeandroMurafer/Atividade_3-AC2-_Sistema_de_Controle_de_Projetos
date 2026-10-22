package br.com.gestao.controller;

import br.com.gestao.dto.DadosProjetoDTO;
import br.com.gestao.dto.FuncionarioDTO;
import br.com.gestao.dto.FuncionarioResumoDTO;
import br.com.gestao.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody FuncionarioDTO funcionario) {
        funcionarioService.adicionar(funcionario);
    }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }

    @GetMapping
    public List<FuncionarioResumoDTO> listar() {
        return funcionarioService.listarTodos();
    }
}
