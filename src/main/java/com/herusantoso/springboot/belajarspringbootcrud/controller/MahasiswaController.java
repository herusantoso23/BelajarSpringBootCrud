package com.herusantoso.springboot.belajarspringbootcrud.controller;

import com.herusantoso.springboot.belajarspringbootcrud.model.Mahasiswa;
import com.herusantoso.springboot.belajarspringbootcrud.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/mahasiswa/edit/{id}", method = RequestMethod.GET)
    public String editData(@PathVariable Integer id, Model model){
        model.addAttribute("mahasiswa", mahasiswaService.getIdMahasiswa(id));
        return "mahasiswa_add";
    }

    @RequestMapping(value = "/mahasiswa/hapus/{id}")
    public String editData(@PathVariable Integer id){
        mahasiswaService.hapus(id);

        return "redirect:/mahasiswa";
    }

}
