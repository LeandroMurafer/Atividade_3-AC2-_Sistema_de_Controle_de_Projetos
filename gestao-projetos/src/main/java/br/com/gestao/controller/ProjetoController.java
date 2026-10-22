package br.com.gestao.controller;

import br.com.gestao.dto.DadosProjetoDTO;
import br.com.gestao.dto.ProjetoDTO;
import br.com.gestao.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody ProjetoDTO projeto) {
        projetoService.adicionar(projeto);
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }

    /** Usado pela tela de projetos. */
    @GetMapping
    public List<DadosProjetoDTO> listar() {
        return projetoService.listarTodos();
    }

    /** Projetos com início no intervalo: /projetos/periodo?inicio=2026-01-01&fim=2026-12-31 */
    @GetMapping("/periodo")
    public List<DadosProjetoDTO> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return projetoService.buscarPorPeriodoDeInicio(inicio, fim);
    }
}
