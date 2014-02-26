/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.service;

import com.agendame.model.Agenda;
import com.agendame.repository.Agendas;
import java.io.Serializable;
import java.util.Calendar;
import javax.inject.Inject;



/**
 *
 * @author Marcos-TSI
 */

public class AgendaService implements Serializable{

    private static final long serialVersionUID= 1L; 
    
    @Inject
    private Agendas agendas;
    
    public Agenda salvar(Agenda agenda) {
        Calendar data = Calendar.getInstance();
        
        if (agenda.getDataFim().getTime() <= agenda.getDataInicio().getTime()) {
            throw new NegocioException("Data Final tem que ser maior que a data Inicial");
        }
        
        return agendas.guardar(agenda);
    }
    
}
