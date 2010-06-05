package com.foo.dao;

import com.foo.data.Musteri;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Altug Bilgin ALTINTAS
 */

@Repository
@Transactional
@Service
public class MusteriDAO {

    @Autowired
	private SessionFactory sessionFactory;

    /**
     * Eposta ya gore Musteri bulur
     * @param eposta Musteri epostasi
     * @return Musteri eger varsa
     */
    public Musteri bul(String eposta) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Musteri.class);
        criteria.add(Restrictions.eq("eposta", eposta));
        List<Musteri> sonuc =  criteria.list();
        if (sonuc!=null && sonuc.size()>0) {
             Musteri musteri = sonuc.get(0);
            return musteri;
        }else {
            return null;
        }        
    }

    /**
     * Musterinin sifresini kontrol eder
     * @param eposta   ilgili musterinin eposta adresi
     * @param sifre    ilgili musterinin sifresi
     * @return   eposta ve sifre dogru ise true, degilse false
     */
    public boolean sifreDogruMu(String eposta, String sifre) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Musteri.class);
        criteria.add(Restrictions.eq("eposta", eposta));
        criteria.add(Restrictions.eq("sifre", sifre));
        List<Musteri> sonuc =  criteria.list();
        if (sonuc!=null && sonuc.size()>0) {
           return true;
        }else {
            return false;
        }
    }

    
}
