/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Agenda;
import com.agendame.model.Servico;
import com.agendame.repository.Agendas;
import com.agendame.repository.Servicos;
import com.agendame.service.AgendaService;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Named(value = "AgendaBean")
@ViewScoped
public class AgendaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AgendaService AgendaService;
    @Inject
    private Servicos servicos;
    @Inject
    private Agendas agendas;

    private ScheduleModel eventoModel;

    private Agenda agenda;
    private Servico servicoSelecionado;

    private List<Servico> servicosRaizes;
    private List<Agenda> agendasRaizes;

    public AgendaBean() {
        limpar();
    }

    public void salvar() {
        this.AgendaService.salvar(this.agenda);
        limpar();
        FacesUtil.addInfoMessage("Agenda Salvo com Sucesso");
    }

    public void inicializar() {

        if (FacesUtil.isNotPostback()) {
            agenda = new Agenda();
            eventoModel = new DefaultScheduleModel();
            servicosRaizes = servicos.raizes();

            //recupera a lista de eventos
            agendasRaizes = agendas.raizes();

            //percorre a lista de eventos e popula o calendario
            for (Agenda a : agendasRaizes) {

                DefaultScheduleEvent evento = new DefaultScheduleEvent();
                evento.setStyleClass(servicoSelecionado.getNome());
                evento.setAllDay(a.isDiaTodo());
                evento.setEndDate(a.getDataFim());
                evento.setStartDate(a.getDataInicio());
                evento.setTitle(a.getDescricao());
                evento.setData(a.getId());
                evento.setEditable(true); 
                eventoModel.addEvent(evento);
            }
        }
    }

    public boolean isEditando() {
        return this.agenda.getId() != null;
    }

    private void limpar() {
        this.agenda = new Agenda();
        this.servicoSelecionado = new Servico();
    }

    public void isNovo(SelectEvent selectEvent) {

        ScheduleEvent evento = new DefaultScheduleEvent("",
                (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        agenda = new Agenda();
        //recupera a data em que o usuario clicou
        agenda.setDataInicio(evento.getStartDate());
        agenda.setDataFim(evento.getEndDate());
    }

    public void isSelecionado(SelectEvent selectEvent) {

        ScheduleEvent evento = (ScheduleEvent) selectEvent.getObject();

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) evento.getData()) {
                agenda = a;
                break;
            }
        }
    }

    public void isMovido(ScheduleEntryMoveEvent event) {

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) event.getScheduleEvent().getData()) {
                agenda = a;
                break;
            }
        }
    }

    public void isRedimensionado(ScheduleEntryResizeEvent event) {

        for (Agenda a : agendasRaizes) {
            if (a.getId() == (Long) event.getScheduleEvent().getData()) {
                agenda = a;
                break;
            }
        }
    }

    public List<Servico> getServicosRaizes() {
        return servicosRaizes;
    }

    public List<Agenda> getAgendasRaizes() {
        return agendasRaizes;
    }

    public void adicionaEnderecoAgenda() {
        this.agenda.getServicos().add(this.servicoSelecionado);
        servicoSelecionado.setAgenda(agenda);
        this.servicoSelecionado = new Servico();
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

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

}
