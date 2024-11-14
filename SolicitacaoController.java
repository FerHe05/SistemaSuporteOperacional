package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping
    public Solicitacao criarSolicitacao(@RequestBody Solicitacao solicitacao) {
        return solicitacaoService.criarSolicitacao(solicitacao);
    }

    @GetMapping
    public List<Solicitacao> listarSolicitacoes() {
        return solicitacaoService.listarSolicitacoes();
    }

    @GetMapping("/{id}")
    public Solicitacao obterSolicitacao(@PathVariable Long id) {
        return solicitacaoService.obterSolicitacao(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
    }

    @PutMapping("/{id}")
    public Solicitacao atualizarSolicitacao(@PathVariable Long id, @RequestBody Solicitacao solicitacao) {
        return solicitacaoService.atualizarSolicitacao(id, solicitacao);
    }

    @DeleteMapping("/{id}")
    public void excluirSolicitacao(@PathVariable Long id) {
        solicitacaoService.excluirSolicitacao(id);
    }
}
