package com.michel.tcp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.michel.tcp.Buffer;
import com.michel.tcp.Chaine;
import com.michel.tcp.Imei;
import com.michel.tcp.Transfert;
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
		
		System.out.println("INFO$: Imei récupéré: " + imei.getCode());
		WebAppSocketApplication.abonnes.add(imei);
		
		return "redirect:/configuration";
	}
	
	@GetMapping("/connexions")
	public String boardConnexions(Model model) {
		
		boolean vide = WebAppSocketApplication.connexions.isEmpty();
		
		model.addAttribute("connexions", WebAppSocketApplication.connexions);
		
		return "connexions";
	}
	
	@GetMapping("/hexadecimal")
	public String hexa(Model model) {
		
		
		Buffer buffer = new Buffer();
		Chaine chaine = new Chaine();
		model.addAttribute("buffer", buffer);
		model.addAttribute("chaine", chaine);
		model.addAttribute("error", false);
		return "hexa";
	}
	
	@GetMapping("/hexadecimal/error")
	public String hexaErr(Model model) {
		
		
		Buffer buffer = new Buffer();
		Chaine chaine = new Chaine();
		model.addAttribute("buffer", buffer);
		model.addAttribute("chaine", chaine);
		model.addAttribute("error", true);
		return "hexa";
	}
	
	@PostMapping("/hexadecimal")
	public String sendHexa(Model model, Buffer buffer) {
		
		boolean error = false;
		try {
			
			buffer.testHex();
			WebAppSocketApplication.buffer.setCode(buffer.getCode());
			WebAppSocketApplication.buffer.setChange(true);
			System.out.println("Code envoyé au client: " + WebAppSocketApplication.buffer.getCode());
			model.addAttribute("error", false);
			return "redirect:/hexadecimal";
			
		}catch (Exception e) {
			
			error = true;
			model.addAttribute("error", error);
			return "redirect:/hexadecimal/error";
			
		}
		
	}
	
	@PostMapping("/string")
	public String chaine(Model model, Chaine chaine) {
		
		WebAppSocketApplication.chaine.setMessage(chaine.getMessage());
		WebAppSocketApplication.chaine.setChange(true);
		System.out.println("String envoyé au client: " + WebAppSocketApplication.chaine.getMessage());
		model.addAttribute("error", false);
		return "redirect:/hexadecimal";
		
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {
		
		WebAppSocketApplication.chaine.setMessage("hello");
		WebAppSocketApplication.chaine.setChange(true);
		System.out.println("String envoyé au client: " + WebAppSocketApplication.chaine.getMessage());
		model.addAttribute("error", false);
		return "redirect:/hexadecimal";
		
	}
	
	@GetMapping("/transferts")
	public String getTransfert(Model model) {
		
		List<Transfert> transferts = WebAppSocketApplication.transferts;
		model.addAttribute("transferts", transferts);
		model.addAttribute("vide", false);
		
		return "transferts";
	}

}
