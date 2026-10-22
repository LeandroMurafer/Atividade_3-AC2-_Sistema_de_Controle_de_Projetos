package br.com.gestao.controller;

import br.com.gestao.dto.DadosSetorDTO;
import br.com.gestao.dto.SetorDTO;
import br.com.gestao.service.SetorService;
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
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody SetorDTO setor) {
        setorService.adicionar(setor);
    }

    @GetMapping("/{idSetor}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
        return setorService.buscarPorId(idSetor);
    }

    @GetMapping
    public List<DadosSetorDTO> listar() {
        return setorService.listarTodos();
    }
}
