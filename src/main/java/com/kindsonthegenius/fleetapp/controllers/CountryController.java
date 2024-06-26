package com.kindsonthegenius.fleetapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.services.CountryService;

@Controller
public class CountryController {

    @Autowired private CountryService countryService;

    //Get All Countrys
    @GetMapping("countries")
    public String findAll(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "country";
    }

    @RequestMapping("/countries/findById/{id}")
    @ResponseBody
    public Optional<Country> findById(@PathVariable Integer id) {
        return countryService.findById(id);
    }


    //Add Country
    @PostMapping(value="countries/addNew")
    public String addNew(Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }

    @RequestMapping(value="countries/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }

    @RequestMapping(value="/countries/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable int id) {
        countryService.delete(id);
        return "redirect:/countries";
    }


}
