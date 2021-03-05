package ua.taras.kushmyruk.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.service.CommissionService;
import ua.taras.kushmyruk.service.FacultyService;

import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {
    private static final Logger logger = LoggerFactory.getLogger(FacultyController.class);

   private final FacultyService facultyService;
   private final CommissionService commissionService;

    public FacultyController(FacultyService facultyService, CommissionService commissionService) {
        this.facultyService = facultyService;
        this.commissionService = commissionService;
    }

    @GetMapping
    public String getListOfFaculty(Model model, @RequestParam(required = false) String sorting) {
        logger.info("Faculties page");
        List<Faculty> faculties = facultyService.getAllSortedFaculties(sorting);
        model.addAttribute("faculties", faculties);
        return "faculty";
    }

    @GetMapping("/redaction")
    public String redactionFaculty( Model model){
        logger.info("Faculty redaction page");
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "facultyRedact";
    }

    @GetMapping("/redaction/{facultyName}")
    public String changeFaculty(@PathVariable String facultyName, Model model){
        logger.info("redact :{} page", facultyName);
        Faculty faculty =  facultyService.getFacultyByName(facultyName);
        model.addAttribute("faculty", faculty);
        model.addAttribute("disciplines", faculty.getRequiredDisciplines());
        model.addAttribute("address", faculty.getFacultyAddress());
        return "changeFaculty";
    }

    @GetMapping("/information/{facultyName}")
    public String getFacultyInformation(@PathVariable String facultyName, Model model){
        logger.info("information :{} page", facultyName);
        model.addAttribute("students", facultyService.getFacultyStudentList(facultyName));
        model.addAttribute("faculty", facultyService.getFacultyByName(facultyName));
        return "facultyInfrom";
    }

    @GetMapping("/accepted/{facultyName}")
    public String getAllAcceptedStudents(@PathVariable String facultyName, Model model){
        logger.info("accepted student list :{} page", facultyName);
        model.addAttribute("studentOrders", commissionService.getAcceptedStudentOrder(facultyName));
        model.addAttribute("faculty", facultyService.getFacultyByName(facultyName));
        return "facultyAcceptedStudents";
    }


    @PostMapping("/redaction")
    public String addFaculty (@RequestParam String facultyName,
                              @RequestParam String disciplines,
                              @RequestParam String freePlaces,
                              @RequestParam String scholarshipPlaces,
                              @RequestParam String city,
                              @RequestParam String street,
                              @RequestParam String building,
                              Model model){
        facultyService.addFaculty(facultyName, Integer.parseInt(freePlaces), Integer.parseInt(scholarshipPlaces),
                city, street, building, disciplines);
        List<Faculty> faculties  = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "facultyRedact";
    }

    @PostMapping("/redaction/{facultyName}")
    public String changeFacultyInformation(@PathVariable String facultyName,
                                           @RequestParam String disciplines,
                                           @RequestParam String freePlaces,
                                           @RequestParam String scholarshipPlaces,
                                           @RequestParam String city,
                                           @RequestParam String street,
                                           @RequestParam String building,
                                           @RequestParam String ... redactedDiscipline
                                           ){
        facultyService.redactFaculty(facultyName, Integer.parseInt(freePlaces), Integer.parseInt(scholarshipPlaces),
                disciplines, city, street, building, redactedDiscipline);
     return "redirect:/faculties/redaction";
    }

    @PostMapping("/information/{facultyName}")
    public String acceptStudents(@PathVariable String facultyName){
      commissionService.acceptStudents(facultyName);
      return "redirect:/faculties/accepted/" + facultyName;
    }
}
