package com.example.selecteur.controller;

import com.example.selecteur.model.DispositifMedical;
import com.example.selecteur.model.LogicielRadiologie;
import com.example.selecteur.repository.DispositifMedicalRepository;
import com.example.selecteur.repository.LogicielRadiologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private DispositifMedicalRepository dispositifMedicalRepository;

    @Autowired
    private LogicielRadiologieRepository logicielRadiologieRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/dispositif-medical")
    public String getDispositifMedical(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "surface", required = false) String surfaceStr,
            Model model) {

        List<DispositifMedical> dispositifs;
        Double surface = null;

        // Convert surface from String to Double
        if (surfaceStr != null && !surfaceStr.isEmpty()) {
            try {
                surface = Double.parseDouble(surfaceStr);
            } catch (NumberFormatException e) {
                surface = null; // Invalid surface value; ignore the filter
            }
        }

        // Fetch dispositifs based on filters
        if ((type != null && !type.isEmpty()) || surface != null) {
            dispositifs = dispositifMedicalRepository.findByTypeOrSurface(type, surface);
 
        } else {
            dispositifs = dispositifMedicalRepository.findAll();
        }

        model.addAttribute("dispositifs", dispositifs);
        return "dispositif-medical";
    }

    @GetMapping("/logiciel-radiologie")
    public String getLogicielRadiologie(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "platform", required = false) String platform,
            Model model) {

        List<LogicielRadiologie> logiciels;
        if ((category != null && !category.isEmpty()) || (platform != null && !platform.isEmpty())) {
            logiciels = logicielRadiologieRepository.findByCategoryOrPlatform(category, platform);
        } else {
            logiciels = logicielRadiologieRepository.findAll();
        }

        model.addAttribute("logiciels", logiciels);
        return "logiciel-radiologie";
    }

    @GetMapping("/admin/dispositif-medical/new")
    public String newDispositifMedicalForm(Model model) {
        model.addAttribute("dispositifMedical", new DispositifMedical());
        return "admin/dispositif-medical-form";
    }

    @PostMapping("/admin/dispositif-medical")
    public String saveDispositifMedical(@ModelAttribute DispositifMedical dispositifMedical) {
        dispositifMedicalRepository.save(dispositifMedical);
        return "redirect:/dispositif-medical";
    }

    @GetMapping("/admin/dispositif-medical/edit/{id}")
    public String editDispositifMedicalForm(@PathVariable("id") Long id, Model model) {
        Optional<DispositifMedical> dispositifMedical = dispositifMedicalRepository.findById(id);
        if (dispositifMedical.isPresent()) {
            model.addAttribute("dispositifMedical", dispositifMedical.get());
            return "admin/dispositif-medical-form";
        } else {
            // Handle case where DispositifMedical is not found
            return "redirect:/dispositif-medical";
        }
    }

    @GetMapping("/admin/dispositif-medical/delete/{id}")
    public String deleteDispositifMedical(@PathVariable("id") Long id) {
        if (dispositifMedicalRepository.existsById(id)) {
            dispositifMedicalRepository.deleteById(id);
        }
        return "redirect:/dispositif-medical";
    }

    @GetMapping("/admin/logiciel-radiologie/new")
    public String newLogicielRadiologieForm(Model model) {
        model.addAttribute("logicielRadiologie", new LogicielRadiologie());
        return "admin/logiciel-radiologie-form";
    }

    @PostMapping("/admin/logiciel-radiologie")
    public String saveLogicielRadiologie(@ModelAttribute LogicielRadiologie logicielRadiologie) {
        logicielRadiologieRepository.save(logicielRadiologie);
        return "redirect:/logiciel-radiologie";
    }

    @GetMapping("/admin/logiciel-radiologie/edit/{id}")
    public String editLogicielRadiologieForm(@PathVariable("id") Long id, Model model) {
        Optional<LogicielRadiologie> logicielRadiologie = logicielRadiologieRepository.findById(id);
        if (logicielRadiologie.isPresent()) {
            model.addAttribute("logicielRadiologie", logicielRadiologie.get());
            return "admin/logiciel-radiologie-form";
        } else {
            // Handle case where LogicielRadiologie is not found
            return "redirect:/logiciel-radiologie";
        }
    }

    @GetMapping("/admin/logiciel-radiologie/delete/{id}")
    public String deleteLogicielRadiologie(@PathVariable("id") Long id) {
        if (logicielRadiologieRepository.existsById(id)) {
            logicielRadiologieRepository.deleteById(id);
        }
        return "redirect:/logiciel-radiologie";
    }
}
