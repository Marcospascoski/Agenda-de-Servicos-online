 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository;

import com.agendame.model.Cliente;
import com.agendame.repository.filter.ClienteFilter;
import com.agendame.service.NegocioException;
import com.agendame.util.jpa.Transactional;
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
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @Transactional
    public Cliente guardar(Cliente cliente) {
        return em.merge(cliente);
    }

    @Transactional
    public void remover(Cliente cliente) {
        try {
            cliente = porId(cliente.getId());
            em.remove(cliente);
            em.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Servico não pode ser excluído.");
        }
    }

    public List<Cliente> raizes() {
        return em.createQuery("from Clientes", Cliente.class).getResultList();
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

    public Cliente porDocReceitaFederal(String docReceitaFederal) {
        Cliente cliente = null;

        try {
            cliente = this.em.createQuery("from Cliente"
                    + " where docReceitaFederal = :docReceitaFederal", Cliente.class)
                    .setParameter("docReceitaFederal", docReceitaFederal).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return cliente;
    }

    public List<Cliente> porNome(String nome) {
		return this.em.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
}
