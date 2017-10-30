# Belajar Membuat Aplikasi Web menggunakan Spring Boot

##1. Persiapan

Sebelum memulai membuah aplikasi crud sederhana berbasis web menggunakan spring boot, ada yang harus dipersiapkan terlebih dahulu diantaranya :

```
1. JDK 8
2. XAMPP / Mysql
3. Maven
4. IDE (IntelliJ IDEA, Eclipse dll)
5. Buat database baru dengan nama "db_mahasiswa"

```


##2. Membuat project spring boot

Untuk mempermudah membuat project, anda bisa mengunjungi http://start.spring.io/ untuk mengenerate project spring boot. Berikut adalah konfigurasi yang saya gunakan :

```
1. group : com.herusantoso.springboot
2. artifact : BelajarSpringBootCrud
3. Nama	: Belajar Spring Boot Crud
4. Package Name : com.herusantoso.springboot.belajarspringbootcrud
5. Packaging : Jar
6. Java Version : 1.8
```
Setelah di generate maka akan mendownload project spring boot sesuai dengan konfigurasi yang di setting sebelumnya.

##3. Import project spring boot ke IDE

Extract file(zip) tersebut lalu simpan di folder project anda, lalu import atau open project tersebut sesuai dengan IDE yang anda gunakan

##4. Menambah depedencies

Di dalam project, buka file <i>pom.xml</i>. Lalu tambahkan beberapa depedency yang akan digunakan pada project ini.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.2</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

##5. Membuat package

Agar struktur project kita rapih dan mengikuti best practice, buatlah 3 package baru bernama <i>controller</i>, <i>dao</i>, <i>model</i> dan <i>service</i>
##6. Membuat model mahasiswa

Pada package model, buatlah satu class baru bernama <i>Mahasiswa.java</i>.

```java
package com.herusantoso.springboot.belajarspringbootcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {

    @Id
    @Column(name = "nim")
    private int nim;


    @Column(name = "nama")
    private String nama;


    @Column(name = "alamat")
    private String alamat;


    @Column(name = "jurusan")
    private String jurusan;

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}


```

Penjelasan beberapa anotasi hibernate pada class ini:

<i><b>@Entity</b></i> = Untuk menentukan entity pada class </br>
<i><b>@Table</b></i>   = Untuk menentukan nama tabel pada database </br>
<i><b>@Id</b></i>    = Menentukan primary key pada suatu tabel </br>
<i><b>@Column</b></i>   = Untuk menentukan nama column pada database

##7. Membuat class DAO (Data Access Object)

Pada package dao. Buatlah sebuah interface bernama <i>MahasiswaDao.java</i>. Interface ini hanya berisi sebuah methode yang tidak menpunyai definisi (abstrak).

```java
package com.herusantoso.springboot.belajarspringbootcrud.dao;

import com.herusantoso.springboot.belajarspringbootcrud.model.Mahasiswa;

import java.util.List;

public interface MahasiswaDao {
    List<Mahasiswa> listMahasiswa();
    Mahasiswa saveOrUpdate(Mahasiswa mahasiswa);
    Mahasiswa getIdMahasiswa(Integer id);
    void hapus(Integer id);
}
```
##8. Membuat class service

Pada package service, Buatlah sebuah class bernama <i>MahasiswaService.java</i>. Class ini nantinya akan mengimplement sebuah interface <i>MahasiswaDao.java</i> yang sebelumnya dibuat, dengan kata lain sebuah interface nanti akan mewariskan methode-methode yang ada ke dalam class ini.

```java
package com.herusantoso.springboot.belajarspringbootcrud.service;

import com.herusantoso.springboot.belajarspringbootcrud.dao.MahasiswaDao;
import com.herusantoso.springboot.belajarspringbootcrud.model.Mahasiswa;

import java.util.List;

public class MahasiswaService implements MahasiswaDao {

    @Override
    public List<Mahasiswa> listMahasiswa() {
        return null;
    }

    @Override
    public Mahasiswa saveOrUpdate(Mahasiswa mahasiswa) {
        return null;
    }

    @Override
    public Mahasiswa getIdMahasiswa(Integer id) {
        return null;
    }

    @Override
    public void hapus(Integer id) {

    }
}

```

Tambahkan anotasi @Service pada class ini, dan deklarisakan object baru dari EntityManagerFactory lalu buat setter dari object ini dan tambahkan anotasi @Autowired

```java
@Service
public class MahasiswaService implements MahasiswaDao {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
```

Penjelasan dari anotasi pada class ini : </br>
<i><b>@Service</b></i> = Untuk menunjukkan sebuah class yang akan digunakan sebagai pusat service dari aplikasi yang akan dibangun </br>
<i><b>@Autowired</b></i>   = Untuk menyederhanakan penggunaan setter method untuk memanggil sebuah class pada depedency injection </br>

###8.1. Membuat methode untuk menampilkan semua data

Dalam methode List tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses select data dari database.

```java
@Override
public List<Mahasiswa> listMahasiswa() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    return entityManager.createQuery("from Mahasiswa", Mahasiswa.class).getResultList();
    }
```

###8.2. Membuat methode untuk menambah atau update data

Dalam methode saveOrUpdate() tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses tambah atau update data ke database.

```java
@Override
public Mahasiswa saveOrUpdate(Mahasiswa mahasiswa) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Mahasiswa saved = entityManager.merge(mahasiswa);
    entityManager.getTransaction().commit();
    
    return saved;
}
```

###8.3. Membuat methode untuk mengambil id

Dalam methode getIdMahasiswa tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses mengambil id

```java
@Override
public Mahasiswa getIdMahasiswa(Integer id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    return entityManager.find(Mahasiswa.class, id);
}
```

###8.4. Membuat methode untuk menghapus

Dalam methode hapus() tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses hapus data berdasarkan id

```java
@Override
public void hapus(Integer id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.find(Mahasiswa.class, id));
    entityManager.getTransaction().commit();
}
```
##9. Menggunakan bootstrap

Download bootstrap di situsnya, anda dapat mengunjungi http://getbootstrap.com/ 
##10. Membuat halaman web
###10.1. Halaman Mahasiswa
Pada halam ini akan menampilkan data