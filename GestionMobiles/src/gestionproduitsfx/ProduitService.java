package gestionproduitsfx;

import classes.javabeans.Produit;
import fichiershibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProduitService {

    private static Session session;

    public static void openSession() {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void closeSession() {
        session.close();
    }

    public static void create(Produit p) {
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
    }

    public static Produit loadProduit(int id) {
        Transaction tx = session.beginTransaction();
        Produit p = (Produit) session.get(Produit.class, id);
        tx.commit();
        return p;
    }

    public static void update(Produit p) {
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
    }

    public static void delete(Produit p) {
        Transaction tx = session.beginTransaction();
        session.delete(p);
        tx.commit();
    }

    public static List<Produit> listProduit() {
        Transaction tx = session.beginTransaction();
        List<Produit> list = session.createQuery("from Produit").list();
        tx.commit();
        return list;
    }
}