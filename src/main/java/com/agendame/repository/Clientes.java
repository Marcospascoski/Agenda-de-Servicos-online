/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository;

import com.agendame.model.Cliente;
import com.agendame.repository.filter.ClienteFilter;
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
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public Cliente guardar(Cliente cliente) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        cliente = em.merge(cliente);
        et.commit();
        return cliente;
    }

    public List<Cliente> raizes() {
        return em.createNamedQuery("from Clientes", Cliente.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> filtrados(ClienteFilter clienteFilter) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);

        // where nome like '%marcos%'
        if (StringUtils.isNotBlank(clienteFilter.getNome())) {
            criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }

}
