/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository;

import com.agendame.model.Usuario;
import com.agendame.repository.filter.UsuarioFilter;
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
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @Transactional
    public Usuario guardar(Usuario usuario) {
        return em.merge(usuario);
    }

    @Transactional
    public void remover(Usuario usuario) {
        try {
            usuario = porId(usuario.getId());
            em.remove(usuario);
            em.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuario não pode ser excluído.");
        }
    }

    public List<Usuario> raizes() {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    public List<Usuario> profissionais() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.em.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}
    
    @SuppressWarnings("unchecked")
    public List<Usuario> filtrados(UsuarioFilter filtro) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);

        // where nome like '%marcos%'
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filtro.getEmail())) {
            criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Usuario porId(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario porEmail(String email) {
        Usuario usuario = null;

        try {
            usuario = this.em.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return usuario;
    }
}
