package com.agendame.util.jsf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.agendame.service.NegocioException;
import java.io.IOException;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Marcos-TSI
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private static final Log log = LogFactory.getLog(JsfExceptionHandler.class);

    private final ExceptionHandler wrapped;

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = context.getException();

            boolean handled = false;
            NegocioException negocioException = getNegocioException(exception);

            try {
                if (exception instanceof ViewExpiredException) {
                    handled = true;
                    redireciona("/");
                } else if (negocioException != null) {
                    handled = true;
                    FacesUtil.addErrorMessage(negocioException.getMessage());
                } else {
                    handled = true;
                    log.error("Erro de sistema: " + exception.getMessage(), exception);
                    redireciona("/erro.xhtml");
                }
            } finally {
                if (handled) {
                    events.remove();
                }
            }
        }
        getWrapped().handle();
    }

    private NegocioException getNegocioException(Throwable exception) {
        if (exception instanceof NegocioException) {
            return (NegocioException) exception;
        } else if (exception.getCause() != null) {
            return getNegocioException(exception.getCause());
        }
        return null;
    }

    private void redireciona(String pagina) {
        try {
            FacesContext facescontext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facescontext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath();

            externalContext.redirect(contextPath + pagina);
            facescontext.responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }
}
