package ua.taras.kushmyruk.controller;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.StudentService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

import java.time.LocalDate;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;
    private final UserServiceImpl userService;

    public StudentController(StudentService studentService, UserServiceImpl userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping()
    public String getUserAccount(@AuthenticationPrincipal User user, Model model){
        logger.info(":{} account page", user.getUsername());
         model.addAttribute("studentStudentOrders", user.getStudentOrders() );
        return "userAccount";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(@AuthenticationPrincipal User user, Model model){
        logger.info("Change :{} password page", user.getUsername() );
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("email", user.getEmail());
        return "changePassword";
    }

    @GetMapping("/messages")
    public String getMessages(@AuthenticationPrincipal User user, Model model){
        logger.info("Messages :{}", user.getUsername());
        model.addAttribute("isReadNotifications", studentService.getReadNotification(user));
        model.addAttribute("notReadNotifications", studentService.getNotReadNotification(user));
        return "messages" ;
    }


    @GetMapping("/change-student-order")
    public String changeStudentOrderPage(@AuthenticationPrincipal User user, Model model){
        logger.info("Start changing student order :{} page", user.getUsername());
          model.addAttribute("studentOrders", studentService.getSO(user));
        return "changeStudentOrder";
    }


    @GetMapping("/change-student-order/{facultyName}/personal")
    public String changePersonalInformationPage(@AuthenticationPrincipal User user,
                                                @PathVariable String facultyName,
                                                Model model){
        logger.info("Start changing student order :{} page", user.getUsername());
        model.addAttribute("facultyName", facultyName);
        model.addAttribute("personalInfo", studentService.getStudentPersonalInfo(user, facultyName));
        return "changeStudentOrderPersonal";

    }

    @GetMapping("/change-student-order/{facultyName}/address")
    public String changeAddressInformationPage(
            @AuthenticationPrincipal User user,
            @PathVariable String facultyName,
            Model model
    ){
        logger.info("Change address student order :{} page", user.getUsername());
        model.addAttribute("facultyName", facultyName);
        model.addAttribute("addressInfo", studentService.getStudentAddress(user, facultyName));
        return "changeStudentOrderAddress";
    }

    @GetMapping("/change-student-order/{facultyName}/passport")
    public String changePassportInformationPage(
            @AuthenticationPrincipal User user,
            @PathVariable String facultyName,
            Model model
    ){
        logger.info("Change passport student order :{} page", user.getUsername());
        model.addAttribute("facultyName", facultyName);
        model.addAttribute("passportInfo", studentService.getStudentPassportInfo(user, facultyName));
        return "changeStudentOrderPassport";
    }

    @GetMapping("/change-student-order/{facultyName}/certificate")
    public String changeCertificateInformationPage(
            @AuthenticationPrincipal User user,
            @PathVariable String facultyName,
            Model model
    ){
        logger.info("Change certificate student order :{} page", user.getUsername());
        model.addAttribute("facultyName", facultyName);
        model.addAttribute("certificateInfo", studentService.getStudentCertificateInfo(user, facultyName));
        return "changeStudentOrderCertificate";
    }


    @PostMapping("/change-password")
    public String changePassword(@AuthenticationPrincipal User user,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 Model model){
        String message =  userService.changePassword(user, password, email);
        model.addAttribute("Message", message);
        return "redirect:/student";
    }

    @PostMapping("/messages")
    public String readMessages(@AuthenticationPrincipal User user,
                               @RequestParam(required = false) String isRead){
        System.out.println(isRead);
    studentService.readMessage(user, isRead);
    return "messages";
    }

    @PostMapping("/change-student-order/{facultyName}/personal")
    public String changePersonalInformation(@AuthenticationPrincipal User user,
                                            @PathVariable String facultyName,
                                            @RequestParam String firstName,
                                            @RequestParam String middleName,
                                            @RequestParam String lastName,
                                            @RequestParam String dateOfBirth,
                                            @RequestParam String nationality,
                                            @RequestParam String gender){
        studentService.changeStudentPersonalInfo(user, facultyName, firstName, middleName, lastName,
                gender,  nationality, LocalDate.parse(dateOfBirth));
        return "redirect:/student/change-student-order";

    }
    @PostMapping("/change-student-order/{facultyName}/address")
    public String changeAddressInformation(@AuthenticationPrincipal User user,
                                           @PathVariable String facultyName,
                                           @RequestParam String city,
                                           @RequestParam String street,
                                           @RequestParam String building,
                                           @RequestParam String apartment,
                                           @RequestParam String postCode){
        studentService.changeStudentAddressInfo(user, facultyName, city, street, building, apartment, postCode);
        return "redirect:/student/change-student-order";
    }

    @PostMapping("/change-student-order/{facultyName}/passport")
    public String changePassportInformation(@AuthenticationPrincipal User user,
                                            @PathVariable String facultyName,
                                            @RequestParam String passportSeria,
                                            @RequestParam String passportNumber,
                                            @RequestParam String registrationOffice,
                                            @RequestParam String issueDate,
                                            @RequestParam MultipartFile passportPhotofile){
        studentService.changeStudentPassportInfo(user, facultyName, passportSeria, passportNumber, registrationOffice,
                LocalDate.parse(issueDate), passportPhotofile);
        return "redirect:/student/change-student-order";
    }

    @PostMapping("/change-student-order/{facultyName}/certificate")
    public String changeCertificateInformation(@AuthenticationPrincipal User user,
                                               @PathVariable String facultyName,
                                               @RequestParam String certificateNumber,
                                               @RequestParam String schoolName,
                                               @RequestParam String endDate,
                                               @RequestParam MultipartFile certificatePhotofile,
                                               @RequestParam String ... scores){

        studentService.changeStudentCertificateInfo(user,facultyName, certificateNumber, schoolName,
                LocalDate.parse(endDate), scores, certificatePhotofile);
        return "redirect:/student/change-student-order";
    }




}
