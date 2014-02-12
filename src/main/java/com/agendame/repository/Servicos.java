/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Servico;
import com.agendame.repository.filter.ServicoFilter;
import com.agendame.service.NegocioException;
import com.agendame.util.jpa.Transactional;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
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
    
    @Transactional
    public Servico guardar(Servico servico){
         return em.merge(servico);
    }
    
    @Transactional
    public void remover(Servico servico) {
        try {
            servico = porId(servico.getId());
            em.remove(servico);
            em.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Servico não pode ser excluído.");
        }
    }
    
    public List<Servico> raizes(){
        return em.createQuery("from Servico", Servico.class).getResultList();    
    }
    
        @SuppressWarnings("unchecked")
    public List<Servico> filtrados(ServicoFilter filtro) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Servico.class);

        // where nome like '%marcos%'
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Servico porId(Long id) {
        return em.find(Servico.class, id);
    }
    
    public Servico porNome(String nome){
        Servico servico = null;

        try {
            servico = this.em.createQuery("from Servico where nome = :nome", Servico.class)
                    .setParameter("nome", nome).getSingleResult();
        } catch (NoResultException e) {
            //Nenhum servico encontrado com o nome informado
        }

        return servico;
    }
    
}
