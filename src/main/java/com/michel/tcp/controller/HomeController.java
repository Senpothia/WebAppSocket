package com.michel.tcp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.michel.tcp.Imei;
import com.michel.tcp.WebAppSocketApplication;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String accueil() {
		
		return "accueil";
		
	}
	
	@GetMapping("/configuration")
	public String accessConfig(Model model) {
		
		Imei imei = new Imei();
		model.addAttribute("imei", imei);
		model.addAttribute("abonnes", WebAppSocketApplication.abonnes);
		
		return "config";
	}
	
	@PostMapping("/configuration")
	public String enregistrement(Imei imei) {
		
		System.out.println("Imei récupéré: " + imei.getCode());
		WebAppSocketApplication.abonnes.add(imei);
		
		return "redirect:/configuration";
	}

}
