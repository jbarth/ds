/*
 * 
 */
package com.ubs.gtp.data.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

/**
 * The Class AbstractHibernateDAO.
 *
 * @param <T> the generic type
 * @author Alexandra.Ojha
 * @author Jakub D Kozlowski
 * @author Willy Lai
 * @since 0.1
 */
@Transactional
public abstract class AbstractHibernateDAO<T extends Serializable> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    protected final Class<T> clazz;

    /**
     * Default constructor.
     *
     * @param clazz class to manage.
     * @throws IllegalArgumentException if {@code clazz} is empty.
     */
    public AbstractHibernateDAO(final Class<T> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    /**
     * Gets the entity by id.
     *
     * @param id the id of the entity to get.
     * @return the entity or {@link Optional#absent()}.
     */
    public Optional<T> getById(Serializable id) {
        return Optional.fromNullable((T) this.getCurrentSession().get(this.clazz, id));
    }

    /**
     * Gets all entries in table.
     *
     * @return all
     */
    public List<T> getAll() {
        return this.getCurrentSession()
                .createQuery("from " + this.clazz.getName()).list();
    }

    /**
     * Persists the entity in the db.
     *
     * @param entity the entity
     */
    public void create(final T entity) {
        Session session = this.getCurrentSession();
        session.save(entity);
    }

    /**
     * Updates entity in db.
     *
     * @param entity the entity
     */
    public void update(final T entity) {
        Session session = this.getCurrentSession();
        session.update(entity);
    }

    /**
     * Delete by id.
     *
     * @param entityId the entity id
     */
    public void deleteById(final Serializable entityId) {
        final Optional<T> entity = this.getById(entityId);
        if (entity.isPresent()) {
            this.delete(entity.get());
        }
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    private void delete(T entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
    }

    /**
     * Gets the current session from session factory.
     *
     * @return the current session
     */
    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }


}
