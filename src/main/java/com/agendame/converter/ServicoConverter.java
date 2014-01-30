package com.agendame.converter;

import com.agendame.model.Servico;
import com.agendame.repository.Servicos;
import com.agendame.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Servico.class)
public class ServicoConverter implements Converter {

	//@Inject
	private Servicos servicos;
	
	public ServicoConverter() {
		servicos = CDIServiceLocator.getBean(Servicos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Servico retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = servicos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Servico servico = (Servico) value;
			return servico.getId() == null ? null : servico.getId().toString();
		}
		
		return "";
	}

}
