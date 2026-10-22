package br.com.gestao.service;

import br.com.gestao.dto.DadosProjetoDTO;
import br.com.gestao.dto.ProjetoDTO;
import br.com.gestao.exception.RecursoNaoEncontradoException;
import br.com.gestao.exception.RegraNegocioException;
import br.com.gestao.model.Funcionario;
import br.com.gestao.model.Projeto;
import br.com.gestao.repository.FuncionarioRepository;
import br.com.gestao.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public void adicionar(ProjetoDTO dto) {
        if (dto.dataFim() != null && dto.dataFim().isBefore(dto.dataInicio())) {
            throw new RegraNegocioException("A data de fim não pode ser anterior à data de início.");
        }
        Projeto projeto = new Projeto();
        projeto.setNome(dto.nome().trim());
        projeto.setDescricao(dto.descricao() == null ? null : dto.descricao().trim());
        projeto.setDataInicio(dto.dataInicio());
        projeto.setDataFim(dto.dataFim());
        projetoRepository.save(projeto);
    }

    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.buscarComFuncionarios(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto " + id + " não encontrado."));
        return DadosProjetoDTO.de(projeto);
    }

    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.buscarComFuncionarios(idProjeto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto " + idProjeto + " não encontrado."));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário " + idFuncionario + " não encontrado."));

        boolean jaVinculado = projeto.getFuncionarios().stream()
                .anyMatch(f -> f.getId().equals(idFuncionario));
        if (jaVinculado) {
            throw new RegraNegocioException("O funcionário já está vinculado a este projeto.");
        }
        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.listarTodosComFuncionarios().stream().map(DadosProjetoDTO::de).toList();
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarPorPeriodoDeInicio(LocalDate inicio, LocalDate fim) {
        if (fim.isBefore(inicio)) {
            throw new RegraNegocioException("A data final do intervalo não pode ser anterior à inicial.");
        }
        return projetoRepository.buscarPorPeriodoDeInicio(inicio, fim).stream()
                .map(p -> new DadosProjetoDTO(p.getId(), p.getNome(), p.getDescricao(),
                        p.getDataInicio(), p.getDataFim(), List.of()))
                .toList();
    }
}
