package com.herusantoso.springboot.belajarspringbootcrud.service;

import com.herusantoso.springboot.belajarspringbootcrud.dao.MahasiswaDao;
import com.herusantoso.springboot.belajarspringbootcrud.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class MahasiswaService implements MahasiswaDao {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Mahasiswa> listMahasiswa() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager.createQuery("from Mahasiswa", Mahasiswa.class).getResultList();
    }

    @Override
    public Mahasiswa saveOrUpdate(Mahasiswa mahasiswa) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Mahasiswa saved = entityManager.merge(mahasiswa);
        entityManager.getTransaction().commit();

        return saved;
    }

    @Override
    public Mahasiswa getIdMahasiswa(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager.find(Mahasiswa.class, id);
    }

    @Override
    public void hapus(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Mahasiswa.class, id));
        entityManager.getTransaction().commit();
    }
}
