package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime; // Adicione esta linha
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    public Solicitacao criarSolicitacao(Solicitacao solicitacao) {
        solicitacao.setDataAbertura(LocalDateTime.now());
        solicitacao.setStatus(Status.ABERTO);
        return solicitacaoRepository.save(solicitacao);
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solicitacaoRepository.findAll();
    }

    public Optional<Solicitacao> obterSolicitacao(Long id) {
        return solicitacaoRepository.findById(id);
    }

    public Solicitacao atualizarSolicitacao(Long id, Solicitacao solicitacaoAtualizada) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus(solicitacaoAtualizada.getStatus());
            solicitacao.setDescricao(solicitacaoAtualizada.getDescricao());
            solicitacao.setPrioridade(solicitacaoAtualizada.getPrioridade());
            return solicitacaoRepository.save(solicitacao);
        }

        throw new RuntimeException("Solicitação não encontrada");
    }

    public void excluirSolicitacao(Long id) {
        solicitacaoRepository.deleteById(id);
    }
}
