# Belajar Membuat Aplikasi Web menggunakan Spring Boot JPA

## Persiapan

Sebelum memulai membuah aplikasi crud sederhana berbasis web menggunakan spring boot, ada yang harus dipersiapkan terlebih dahulu diantaranya :

```
1. JDK 8
2. XAMPP / Mysql
3. Maven
4. IDE (IntelliJ IDEA, Eclipse dll)

```

## Apa saja yang teknologi yang digunakan pada aplikasi ini?

```
1. Spring Boot
2. Hibernate
2. Thymeleaf
3. Mysql
4. Bootstrap
```



## Membuat project spring boot

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

## Import project spring boot ke IDE

Extract file(zip) tersebut lalu simpan di folder project anda, lalu import atau open project tersebut sesuai dengan IDE yang anda gunakan

## Menambah depedencies

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

## Konfigurasi database di spring boot

Download bootstrap, anda dapat mengunjungi http://getbootstrap.com/ . Extract file archieve tersebut lalu taruh di <i>src/main/resources/static</i> .

## Membuat package

1. Buat database dengan nama "db_mahasiswa"
2. Buka appication.properties, dan tambahkan beberapa code seperti dibawah ini :

```xml
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/db_mahasiswa?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username = root
spring.datasource.password =

spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = create-drop
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect


```

## Membuat model mahasiswa

Pada package model, buatlah satu class baru bernama <i>Mahasiswa.java</i>.

```java
package com.herusantoso.springboot.belajarspringbootcrud.model;

import javax.persistence.*;

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

    @Version
    @Column(name = "optVersion", columnDefinition = "integer DEFAULT 0")
    private Integer version;
    

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}



```

Penjelasan beberapa anotasi hibernate pada class ini:

<i><b>@Entity</b></i> = Untuk menentukan entity pada class </br>
<i><b>@Table</b></i>   = Untuk menentukan nama tabel pada database </br>
<i><b>@Id</b></i>    = Menentukan primary key pada suatu tabel </br>
<i><b>@Column</b></i>   = Untuk menentukan nama column pada database

## Membuat class DAO (Data Access Object)

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
## Membuat class service

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

##### 1. Membuat methode untuk menampilkan semua data

Dalam methode List tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses select data dari database.

```java
@Override
public List<Mahasiswa> listMahasiswa() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    return entityManager.createQuery("from Mahasiswa", Mahasiswa.class).getResultList();
    }
```

##### 2. Membuat methode untuk menambah atau update data

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

##### 3. Membuat methode untuk mengambil id

Dalam methode getIdMahasiswa tambahkan beberapa kode seperti dibawah ini, methode ini nantinya akan melakukan proses mengambil id

```java
@Override
public Mahasiswa getIdMahasiswa(Integer id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    return entityManager.find(Mahasiswa.class, id);
}
```

##### 4. Membuat methode untuk menghapus

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
## Menggunakan bootstrap

Download bootstrap, anda dapat mengunjungi http://getbootstrap.com/ . Extract file archieve tersebut lalu taruh di <i>src/main/resources/static</i> .

## Membuat halaman web

##### 1.Menampilkan Data Mahasiswa
Halaman ini akan menampilkan semua data mahasiswa yang ada di database.

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head lang="en">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <title>Starter Template for Bootstrap</title>

    <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet" media="screen"/>

</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Spring Boot Crud Example</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/mahasiswa">Mahasiswa</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </div>
    </nav>


    <div class="container">
        <div th:if="${not #lists.isEmpty(mahasiswa)}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nim</th>
                        <th>Nama</th>
                        <th>Alamat</th>
                        <th>Jurusan</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="mahasiswas : ${mahasiswa}">
                        <td th:text="${mahasiswas.nim}"></td>
                        <td th:text="${mahasiswas.nama}"></td>
                        <td th:text="${mahasiswas.alamat}"></td>
                        <td th:text="${mahasiswas.jurusan}"></td>
                        <td>
                            <a href="">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                            <a href="">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
            <a class="btn btn-default" href="#">Tambah Data</a>
        </div>
    </div>
    
<script th:src="@{/js/jquery.min.js}"></script>
<script th:src="@{/js/bootstrap.min.js}"></script>

</body>
</html>

```

Pada package controller, buat sebuah class bernama <i>MahasiswaController.java</i>. Class ini berfungsi untuk menghubungkan antara class model dan view.

```java
package com.herusantoso.springboot.belajarspringbootcrud.controller;

import com.herusantoso.springboot.belajarspringbootcrud.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MahasiswaController {

    private MahasiswaService mahasiswaService;

    @Autowired
    public void setMahasiswaService(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }

    @RequestMapping(value = "/mahasiswa")
    public String MahasiswaList(Model model){
        model.addAttribute("mahasiswa", mahasiswaService.listMahasiswa());
        return "mahasiswa";
    }
}

```

Jalankan perintah <i>mvn spring-boot:run</i> untuk menjalankan project, buka browser dengan url <i>http://localhost:8080/mahasiswa</i>

#### 2. Tambah Data Mahasiswa
Buat sebuah file dengan nama "halaman_add". Halaman ini akan menampilkan form isian mahasiswa, nantinya akan melakukan tambah data ke database.

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head lang="en">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <title>Starter Template for Bootstrap</title>

    <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet" media="screen"/>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Spring Boot Crud Example</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/mahasiswa">Mahasiswa</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="container">
    <h1>Mahasiswa</h1>

    <div class="row">
        <div class="col-sm-8">
            <form class="form-horizontal" th:object="${mahasiswa}" th:action="@{/mahasiswa/add}" method="post">
                <input type="hidden" th:field="*{version}" />

                <div class="form-group">
                    <label class="col-sm-2 control-label">Nim</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" th:field="*{nim}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Nama</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" th:field="*{nama}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Alamat</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" th:field="*{alamat}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Jurusan</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" th:field="*{jurusan}"/>
                    </div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-default">Simpan</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script th:src="@{/js/jquery.min.js}"></script>
<script th:src="@{/js/bootstrap.min.js}"></script>

</body>
</html>
```

Pada Class MahasiswaController.java, tambahkan 2 buah methode untuk berpindah ke halaman mahasiswa_add dan melakukan proses tambah data 

```java
@RequestMapping(value = "/mahasiswa/add", method = RequestMethod.GET)
public String tampilForm(Model model){
    model.addAttribute("mahasiswa", new Mahasiswa());
    
    return "mahasiswa_add";
}

@RequestMapping(value = "/mahasiswa/add", method = RequestMethod.POST)
public String simpanData(Model model, Mahasiswa mahasiswa){
    model.addAttribute("mahasiswa", mahasiswaService.saveOrUpdate(mahasiswa));
    
    return "redirect:/mahasiswa";
}
```


Jalankan perintah <i>mvn spring-boot:run</i> untuk menjalankan project, buka browser dengan url <i>http://localhost:8080/mahasiswa</i>

#### 3. Edit Data Mahasiswa

Pada halaman mahasiswa.html, edit pada bagian link edit seperti dibawah ini :

```html
<a th:href="${'/mahasiswa/edit/'+ mahasiswas.nim}">
    <span class="glyphicon glyphicon-edit"></span>
</a>
```

dan pada MahasiswaController.java tambahkan metode untuk berpindah halaman form mahasiswa dengan melakukan get nim.

```java
@RequestMapping(value = "/mahasiswa/edit/{id}", method = RequestMethod.GET)
public String editData(@PathVariable Integer id, Model model){
    model.addAttribute("mahasiswa", mahasiswaService.getIdMahasiswa(id));
    
    return "mahasiswa_add";
}
```

Jalankan perintah <i>mvn spring-boot:run</i> untuk menjalankan project, buka browser dengan url <i>http://localhost:8080/mahasiswa</i>

#### 4. Hapus Data Mahasiswa

Pada halaman mahasiswa.html, edit pada bagian link hapus seperti dibawah ini :

```html
<a th:href="${'/mahasiswa/hapus/'+mahasiswas.nim}">
    <span class="glyphicon glyphicon-remove"></span>
</a>
```

dan pada MahasiswaController.java tambahkan metode untuk menghapus data dengan melakukan get nim.

```java
@RequestMapping(value = "/mahasiswa/hapus/{id}")
public String editData(@PathVariable Integer id){
    mahasiswaService.hapus(id);

    return "redirect:/mahasiswa";
}
```
