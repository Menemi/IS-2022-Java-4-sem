package ru.itmo.kotiki.dao;

import org.hibernate.*;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.hibernate.HibernateFactory;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public Owner findById(int id) {
        return HibernateFactory.getSessionFactory().openSession().get(Owner.class, id);
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public Cat findCatById(int id) {
        return HibernateFactory.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = (List<Owner>) HibernateFactory.getSessionFactory().openSession().createQuery("From Owner").list();
        return owners;
    }
}
