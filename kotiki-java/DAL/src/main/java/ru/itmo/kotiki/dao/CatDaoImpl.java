package ru.itmo.kotiki.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.kotiki.hibernate.HibernateFactory;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

import java.util.List;

public class CatDaoImpl implements CatDao {
    @Override
    public Cat findById(int id) {
        return HibernateFactory.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public void save(Cat cat) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Cat cat) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public Owner findOwnerById(int id) {
        return HibernateFactory.getSessionFactory().openSession().get(Owner.class, id);
    }

    @Override
    public List<Cat> findAll() {
        List<Cat> cats = (List<Cat>) HibernateFactory.getSessionFactory().openSession().createQuery("From Cat").list();
        return cats;
    }
}
