package com.kindsonthegenius.fleetapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kindsonthegenius.fleetapp.models.JobTitle;
import com.kindsonthegenius.fleetapp.services.JobTitleService;

@Controller
public class JobTitleController {
	
	@Autowired private JobTitleService jobTitleService;
	
	//Get All JobTitles
	@GetMapping("jobTitles")
	public String findAll(Model model){		
		model.addAttribute("jobTitles", jobTitleService.findAll());
		return "jobTitle";
	}	
	
	@RequestMapping("jobTitles/findById/{id}")
	@ResponseBody
	public Optional<JobTitle> findById(@PathVariable Integer id)
	{
		return jobTitleService.findById(id);
	}
	
	//Add JobTitle
	@PostMapping(value="jobTitles/addNew")
	public String addNew(JobTitle jobTitle) {
		jobTitleService.save(jobTitle);
		return "redirect:/jobTitles";
	}	
	
	@RequestMapping(value="jobTitles/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(JobTitle jobTitle) {
		jobTitleService.save(jobTitle);
		return "redirect:/jobTitles";
	}

	@RequestMapping(value="/jobTitles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable int id) {
		jobTitleService.delete(id);
		return "redirect:/jobTitles";
	}


}
