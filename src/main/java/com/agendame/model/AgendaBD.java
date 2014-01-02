/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "agendaBD")
@ViewScoped
public class AgendaBD implements Serializable {

    private List<Agenda> listaAgenda;

    public AgendaBD() {
        listaAgenda = new ArrayList<Agenda>();
    }

    public List<Agenda> buscarTodasAgendas() {
        return listaAgenda;
    }

    public void adicionarAgenda(Agenda agenda) {
        agenda.setId(listaAgenda.size() + 1);
        listaAgenda.add(agenda);
    }

    public void removerAvenda(Agenda agenda) {
        listaAgenda.remove(agenda);
    }

}
