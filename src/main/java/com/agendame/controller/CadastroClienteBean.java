/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Cliente;
import com.agendame.model.Endereco;
import com.agendame.model.TipoPessoa;
import com.agendame.repository.Enderecos;
import com.agendame.service.CadastroClienteService;
import com.agendame.service.CadastroEnderecoService;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroClienteBean")
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroClienteService cadastroClienteService;
    @Inject
    private Enderecos enderecos;

    private Cliente cliente;
    private Endereco endereco;
    private Endereco enderecoSelecionado;

    private List<Endereco> enderecoRaizes;

    public CadastroClienteBean() {
        limpar();
    }

    public void salvar() {
        this.cadastroClienteService.salvar(this.cliente);
        limpar();
        FacesUtil.addInfoMessage("Cliente Salvo com Sucesso");
    }

    public void excluir() {
        cliente.getEnderecos().remove(enderecoSelecionado);
        FacesUtil.addInfoMessage("Endereco " + cliente.getEnderecos().size()
                + " excluído com sucesso.");
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            enderecoRaizes = enderecos.raizes();
        }
    }

    public boolean isEditando() {
        return this.cliente.getId() != null;
    }

    private void limpar() {
        this.cliente = new Cliente();
        this.endereco = new Endereco();
    }

    public List<Endereco> getEnderecoRaizes() {
        return enderecoRaizes;
    }

    public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }

    public void adicionaEnderecoCliente() {
        this.cliente.getEnderecos().add(this.endereco);
        endereco.setCliente(cliente);
        this.endereco = new Endereco();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

}
