/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Agenda;
import com.agendame.model.Cliente;
import com.agendame.model.Servico;
import com.agendame.model.Usuario;
import com.agendame.repository.Agendas;
import com.agendame.repository.Clientes;
import com.agendame.repository.Servicos;
import com.agendame.repository.Usuarios;
import com.agendame.service.AgendaService;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroAgendamentoBean")
@ViewScoped
public class CadastroAgendamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;
    @Inject
    private Clientes clientes;
    @Inject
    private Servicos servicos;
    @Inject
    private Agendas agendas;

    @Inject
    private AgendaService AgendaService;

    private ScheduleModel eventoModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    private Agenda agenda;
    private Usuario usuario;
    private Servico servico;
    private Servico servicoSelecionado;

    private List<Usuario> profissionais;
    private List<Servico> servicosRaizes;
    private List<Agenda> agendasRaizes;

    public CadastroAgendamentoBean() {
        limpar();
    }

    public void addEvent(ActionEvent actionEvent) {
        salvar();
    }

    public void salvar() {
        if (agenda.getId() != null) {
            System.out.println("Atualiza evento");
            eventoModel.updateEvent(event);
            this.AgendaService.salvar(this.agenda);
            limpar();
            FacesUtil.addInfoMessage("Agendamento atualizado com sucesso");
        } else {
            System.out.println("Novo Evento");
            eventoModel.addEvent(event);
            AgendaService.salvar(this.agenda);
            limpar();
            FacesUtil.addInfoMessage("Agendamento salvo com sucesso");
        }

        inicializar();
    }

    public void inicializar() {

        if (FacesUtil.isNotPostback()) {
            this.agenda = new Agenda();
            eventoModel = new DefaultScheduleModel();
            this.profissionais = usuarios.profissionais();
            this.servicosRaizes = servicos.raizes();

            //recupera a lista de eventos
            agendasRaizes = agendas.raizes();

            //percorre a lista de eventos e popula o calendario
            for (Agenda a : agendasRaizes) {

                DefaultScheduleEvent evento = new DefaultScheduleEvent();
                evento.setAllDay(a.isDiaTodo());
                evento.setStartDate(a.getDataInicio());
                evento.setEndDate(a.getDataFim());
                evento.setTitle(a.getObservacao());
                evento.setData(a.getId());
                evento.setEditable(true);
                eventoModel.addEvent(evento);
            }
        }
    }

    private void limpar() {
        this.agenda = new Agenda();
        this.servicoSelecionado = new Servico();
    }

    public void onDateSelect(SelectEvent selectEvent) {

        ScheduleEvent evento = new DefaultScheduleEvent("",
                (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        agenda = new Agenda();
        //recupera a data em que o usuario clicou
        agenda.setDataInicio(evento.getStartDate());
        agenda.setDataFim(evento.getEndDate());

    }

    public void onEventSelect(SelectEvent selectEvent) {
        ScheduleEvent evento = (ScheduleEvent) selectEvent.getObject();

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) evento.getData()) {
                agenda = a;
                break;
            }
        }
    }

    public void onEventMove(ScheduleEntryMoveEvent evento) {

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) evento.getScheduleEvent().getData()) {
                agenda = a;
                break;
            }
        }
        salvar();
    }

    public void onEventResize(ScheduleEntryResizeEvent evento) {

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) evento.getScheduleEvent().getData()) {
                agenda = a;
                break;
            }
        }
        salvar();
    }

    public List<Cliente> completarCliente(String nome) {
        return this.clientes.porNome(nome);
    }

    public List<Usuario> getProfissionais() {
        return profissionais;
    }

    public List<Servico> getServicosRaizes() {
        return servicosRaizes;
    }

    public List<Agenda> getAgendasRaizes() {
        return agendasRaizes;
    }

    public ScheduleModel getEventoModel() {
        return eventoModel;
    }

    public void setEventoModel(ScheduleModel eventoModel) {
        this.eventoModel = eventoModel;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

}
