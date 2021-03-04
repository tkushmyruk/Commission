package ua.taras.kushmyruk.service;

import org.springframework.web.multipart.MultipartFile;
import ua.taras.kushmyruk.model.*;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
     List<Notification> getNotReadNotification(User user);

     List<Notification> getReadNotification(User user);

     void readMessage(User user, String isRead);

    String checkIsSendedStudentOrder(User user, String facultyName);

    StudentPersonalInfo getStudentPersonalInfo(User user, String facultyName);

    void changeStudentPersonalInfo(User user, String facultyName, String firstName, String middleName,
                                   String lastName, String gender, String nationality, LocalDate dateOfBirth);

    Address getStudentAddress(User user, String facultyName);

    void changeStudentAddressInfo(User user, String facultyName, String city, String street, String building,
                                  String apartment, String postcode);

    Passport getStudentPassportInfo(User user, String facultyName);

    void changeStudentPassportInfo(User user,String facultyName, String passportSeria, String passportNumber,
                                   String registrationOffice, LocalDate issueDate, MultipartFile passportFile);

    Certificate getStudentCertificateInfo(User user, String facultyName);

    void changeStudentCertificateInfo(User user, String facultyName, String schoolName, String certificateNumber,
                                   LocalDate endSchoolDate, String[] scores, MultipartFile certificateFile);
    List<StudentOrder> getSO(User u);
}
