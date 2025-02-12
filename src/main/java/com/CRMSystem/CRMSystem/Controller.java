package com.CRMSystem.CRMSystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    OperatorRepository operatorRepository;

    @GetMapping("/")
    public String index(Model model) {


      List<ApplicationRequest> applicationRequestList = applicationRepository.findAllWithCourses();

      List<Courses> coursesList = coursesRepository.findAll();

      List<ApplicationRequest> sortedApplications = applicationRequestList.stream()
                      .sorted(Comparator.comparing(ApplicationRequest::isHandled))
              .collect(Collectors.toList());

      model.addAttribute("applicationRequestList", sortedApplications);
      model.addAttribute("coursesList", coursesList);

        return "homepage";
    }

    @GetMapping("/add")
    public String home(Model model) {

        List<Courses> coursesList = coursesRepository.findAll();

        model.addAttribute("coursesList", coursesList);

        return "addApplication";

    }

    @PostMapping("/add")
    public String addApplication(@RequestParam("name") String name, @RequestParam("course") Long courseId,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("commentary") String commentary) {

        ApplicationRequest applicationRequest = new ApplicationRequest();

        applicationRequest.setUserName(name);

        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(commentary);
        applicationRequest.setHandled(false);

        Courses course = coursesRepository.findById(courseId).orElse(null);

        applicationRequest.setCourse(course);

        applicationRepository.save(applicationRequest);

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);

        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);

        model.addAttribute("applicationRequest", applicationRequest);

        List<Operator> operatorList = operatorRepository.findAll();

        model.addAttribute("operatorList", operatorList);


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

    @PostMapping("/operator")
    public String operator(@RequestParam("selectedOperators") List<Long> selectedIds,
                           RedirectAttributes redirectAttributes, @RequestParam("requestId") Long requestId) {

        ApplicationRequest applicationRequest = applicationRepository.findById(requestId).orElse(null);
        applicationRequest.setHandled(true);

        List<Operator> operatorListByIds = operatorRepository.findAllById(selectedIds);

        applicationRequest.setOperator(operatorListByIds);

        applicationRepository.save(applicationRequest);

        redirectAttributes.addFlashAttribute("selectedOpList", operatorListByIds);

        return "redirect:/detail/" + requestId;
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam("requestId") Long requestId) {

        Operator operator =  operatorRepository.findById(id).orElse(null);

        ApplicationRequest applicationRequest = applicationRepository.findById(requestId).orElse(null);

        applicationRequest.getOperator().remove(operator);

        applicationRepository.save(applicationRequest);

        return "redirect:/detail/" + requestId;

    }



}
