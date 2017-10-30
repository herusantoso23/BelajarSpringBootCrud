package com.herusantoso.springboot.belajarspringbootcrud.dao;

import com.herusantoso.springboot.belajarspringbootcrud.model.Mahasiswa;

import java.util.List;

public interface MahasiswaDao {
    List<Mahasiswa> listMahasiswa();
    Mahasiswa saveOrUpdate(Mahasiswa mahasiswa);
    Mahasiswa getIdMahasiswa(Integer id);
    void hapus(Integer id);
}
