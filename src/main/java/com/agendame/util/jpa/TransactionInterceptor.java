/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.util.jpa;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction et = em.getTransaction();
        
        boolean criador = false;
        try {
            if (!et.isActive()) {
                // truque para fazer rollback no que já passou
                // (senão, um futuro commit, confirmaria até mesmo operações sem transação)
                et.begin();
                et.rollback();

                // agora sim inicia a transação
                et.begin();

                criador = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (et != null && criador) {
                et.rollback();
            }

            throw e;
        } finally {
            if (et != null && et.isActive() && criador) {
                et.commit();
            }
        }
    }

}
