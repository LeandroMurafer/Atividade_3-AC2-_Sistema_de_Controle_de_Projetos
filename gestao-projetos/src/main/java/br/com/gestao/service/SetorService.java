package br.com.gestao.service;

import br.com.gestao.dto.DadosSetorDTO;
import br.com.gestao.dto.SetorDTO;
import br.com.gestao.exception.RecursoNaoEncontradoException;
import br.com.gestao.exception.RegraNegocioException;
import br.com.gestao.model.Setor;
import br.com.gestao.repository.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Transactional
    public void adicionar(SetorDTO dto) {
        String nome = dto.nome().trim();
        if (setorRepository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe um setor com o nome '" + nome + "'.");
        }
        Setor setor = new Setor();
        setor.setNome(nome);
        setorRepository.save(setor);
    }

    @Transactional(readOnly = true)
    public DadosSetorDTO buscarPorId(Integer id) {
        Setor setor = setorRepository.buscarComFuncionarios(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor " + id + " não encontrado."));
        return DadosSetorDTO.de(setor);
    }

    @Transactional(readOnly = true)
    public List<DadosSetorDTO> listarTodos() {
        return setorRepository.listarComFuncionarios().stream().map(DadosSetorDTO::de).toList();
    }
}
