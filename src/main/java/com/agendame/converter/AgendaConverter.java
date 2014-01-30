package com.agendame.converter;

import com.agendame.model.Agenda;
import com.agendame.repository.Agendas;
import com.agendame.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Agenda.class)
public class AgendaConverter implements Converter {

	//@Inject
	private Agendas agendas;
	
	public AgendaConverter() {
		agendas = CDIServiceLocator.getBean(Agendas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agenda retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = agendas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Agenda agenda = (Agenda) value;
			return agenda.getId() == null ? null : agenda.getId().toString();
		}
		
		return "";
	}

}
