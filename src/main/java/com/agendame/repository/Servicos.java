/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Servico;
import com.agendame.repository.filter.ServicoFilter;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcos-TSI
 */
public class Servicos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Servico guardar(Servico servico){
        EntityTransaction et = em.getTransaction();
        et.begin();
        servico = em.merge(servico);
        et.commit();
        return servico;
    }
    
    public List<Servico> raizes(){
        return em.createNamedQuery("from Servico", Servico.class).getResultList();    
    }
    
        @SuppressWarnings("unchecked")
    public List<Servico> filtrados(ServicoFilter servicoFilter) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Servico.class);

        // where nome like '%marcos%'
        if (StringUtils.isNotBlank(servicoFilter.getNome())) {
            criteria.add(Restrictions.ilike("nome", servicoFilter.getNome(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Servico porId(Long id) {
        return em.find(Servico.class, id);
    }
    
}
