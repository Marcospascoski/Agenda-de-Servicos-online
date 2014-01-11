/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author Marcos-TSI
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory{

    private final ExceptionHandlerFactory pai;
    
    public JsfExceptionHandlerFactory(ExceptionHandlerFactory pai) {
    this.pai = pai;
   }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionHandler(pai.getExceptionHandler());
    }
    
}
