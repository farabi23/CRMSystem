package com.CRMSystem.CRMSystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    ApplicationRepository applicationRepository;

    @GetMapping("/")
    public String index(Model model) {


      List<ApplicationRequest> applicationRequestList = applicationRepository.findAll();

      model.addAttribute("applicationRequestList", applicationRequestList);

        return "homepage";
    }

    @GetMapping("/add")
    public String home() {
        return "addApplication";
    }

    @PostMapping("/add")
    public String addApplication(@RequestParam("name") String name, @RequestParam("course") String course,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("commentary") String commentary) {

        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setUserName(name);
        applicationRequest.setCourseName(course);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(commentary);
        applicationRequest.setHandled(false);

        applicationRepository.save(applicationRequest);

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);

        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);

        model.addAttribute("applicationRequest", applicationRequest);

        return "detail";
    }

    @PostMapping("/detail/{id}")
    public String edit(@PathVariable Long id, Model model, @RequestParam("action") String action) {

        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);

        if(action.equals("obrabotka")) {
            assert applicationRequest != null;
            applicationRequest.setHandled(true);
            applicationRepository.save(applicationRequest);
            return "redirect:/detail/" + id;
        }
        else{
            assert applicationRequest != null;
            applicationRepository.delete(applicationRequest);
        }

        return "redirect:/";

    }

    @GetMapping("/new")
    public String newApplication(Model model) {
        List<ApplicationRequest> allApplications = applicationRepository.findAll();

        List<ApplicationRequest>  newApplications = allApplications.stream().filter(
                a ->!a.isHandled()
        ).collect(Collectors.toList());

        model.addAttribute("applicationsNew", newApplications);

        return "new";

    }

    @GetMapping("/old")
    public String oldApplication(Model model) {
        List<ApplicationRequest> allApplications = applicationRepository.findAll();

        List<ApplicationRequest> oldApplications = allApplications.stream()
                .filter(a -> a.isHandled())
                .collect(Collectors.toList());

        model.addAttribute("applicationsOld", oldApplications);

        return "old";
    }

}
