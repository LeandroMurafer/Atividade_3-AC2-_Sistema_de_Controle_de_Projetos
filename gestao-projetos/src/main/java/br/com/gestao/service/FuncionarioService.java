package br.com.gestao.service;

import br.com.gestao.dto.DadosProjetoDTO;
import br.com.gestao.dto.FuncionarioDTO;
import br.com.gestao.dto.FuncionarioResumoDTO;
import br.com.gestao.exception.RecursoNaoEncontradoException;
import br.com.gestao.exception.RegraNegocioException;
import br.com.gestao.model.Funcionario;
import br.com.gestao.model.Setor;
import br.com.gestao.repository.FuncionarioRepository;
import br.com.gestao.repository.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Transactional
    public void adicionar(FuncionarioDTO dto) {
        String email = dto.email().trim().toLowerCase();
        if (funcionarioRepository.existsByEmailIgnoreCase(email)) {
            throw new RegraNegocioException("Já existe um funcionário com o e-mail '" + email + "'.");
        }
        Setor setor = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor " + dto.idSetor() + " não encontrado."));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome().trim());
        funcionario.setEmail(email);
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RecursoNaoEncontradoException("Funcionário " + idFuncionario + " não encontrado.");
        }
        return funcionarioRepository.buscarProjetosDoFuncionario(idFuncionario).stream()
                .map(DadosProjetoDTO::de)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResumoDTO> listarTodos() {
        return funcionarioRepository.findAll().stream().map(FuncionarioResumoDTO::de).toList();
    }
}
