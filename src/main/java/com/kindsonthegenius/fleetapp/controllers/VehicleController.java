package com.kindsonthegenius.fleetapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kindsonthegenius.fleetapp.models.Vehicle;
import com.kindsonthegenius.fleetapp.services.EmployeeService;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.VehicleMakeService;
import com.kindsonthegenius.fleetapp.services.VehicleModelService;
import com.kindsonthegenius.fleetapp.services.VehicleService;
import com.kindsonthegenius.fleetapp.services.VehicleStatusService;
import com.kindsonthegenius.fleetapp.services.VehicleTypeService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class VehicleController {
	
	@Autowired private VehicleService vehicleService;
	@Autowired private VehicleTypeService vehicleTypeService;
	@Autowired private VehicleMakeService vehicleMakeService;
	@Autowired private VehicleModelService vehicleModelService;
	@Autowired private LocationService locationService;
	@Autowired private EmployeeService employeeService ;
	@Autowired private VehicleStatusService vehicleStatusService;
	private Vehicle vehicle;

	//Get All Vehicles
	@GetMapping("vehicles")
	public String findAll(Model model){		
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());

		return "vehicle";
	}	
	
	@RequestMapping("vehicles/findById/{id}")
	@ResponseBody
	public Optional<Vehicle> findById(Integer id)
	{
		return vehicleService.findById(id);
	}

	//Add Vehicle
	@Value("${upload.path}")
	private String uploadPath;  // Path to save images

	@PostMapping(value = "vehicles/addNew")
	public String addNew(@RequestParam("photo") MultipartFile file, Vehicle vehicle) {
		try {
			// Save the vehicle entity to get the generated id

			// Set the image bytes to the vehicle entity
			if (!file.isEmpty()) {
				String imageName = "image_" + vehicle.getId() + ".jpg";  // Construct image name
				vehicle.setImageName(imageName);

				// Save image to the static/images directory
				Path imagePath = Paths.get(uploadPath + "/" + imageName);
				Files.write(imagePath, file.getBytes());

				vehicleService.save(vehicle);  // Update the vehicle with the image
			}

			return "redirect:/vehicles";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error uploading image";
		}
	}
	
	@RequestMapping(value="vehicles/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="vehicles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
}
