package com.legal_system.mediation.Controller.Mediators;
import com.legal_system.mediation.Service.MediatorsService;
import com.legal_system.mediation.model.Mediators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/mediators")
public class MediatorHomeController {

    @Autowired
    private MediatorsService mediatorService;
    // List all mediators
    @GetMapping("/mediators-profiles")
    public String showAllMediators(Model model){
        model.addAttribute("mediators", mediatorService.getAllMediators());
        return "Mediators/index";  // your mediators listing page
    }
    // Dynamic profile page for each mediator
    @GetMapping("/{id}")
    public String viewMediatorProfile(@PathVariable int id, Model model){
        Mediators mediator = mediatorService.findMediator(id);
        model.addAttribute("mediator", mediator);
        return "Mediators/mediatorsProfile"; // common mediator profile HTML
    }
    // Update profile (optional)
//    @GetMapping("/update-profile/{id}")
//    public String updateProfile(Model model, @PathVariable int id){
//        Mediators theMediator = mediatorService.findMediator(id);
//        model.addAttribute("action_path","/save-profile");
//        model.addAttribute("mediatorProfessionalDetails", theMediator);
//        return "Mediators/updateProfile";
//    }
}
