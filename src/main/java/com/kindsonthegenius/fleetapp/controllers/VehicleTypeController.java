package com.kindsonthegenius.fleetapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kindsonthegenius.fleetapp.models.VehicleType;
import com.kindsonthegenius.fleetapp.services.VehicleTypeService;

@Controller
public class VehicleTypeController {
	
	@Autowired private VehicleTypeService vehicleTypeService;
	
	//Get All VehicleTypes
	@GetMapping("vehicleTypes")
	public String findAll(Model model){		
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		return "vehicleType";
	}	
	
	@RequestMapping("vehicleTypes/findById/{id}")
	@ResponseBody
	public Optional<VehicleType> findById(@PathVariable Integer id)
	{
		return vehicleTypeService.findById(id);
	}
	
	//Add VehicleType
	@PostMapping(value="vehicleTypes/addNew")
	public String addNew(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicleTypes";
	}	
	
	@RequestMapping(value="vehicleTypes/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicleTypes";
	}
	
	@RequestMapping(value="vehicleTypes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleTypeService.delete(id);
		return "redirect:/vehicleTypes";
	}


}
