package ua.taras.kushmyruk.service.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.taras.kushmyruk.model.*;
import ua.taras.kushmyruk.repository.*;
import ua.taras.kushmyruk.service.StudentService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final String UPLOAD_PATH = "/Users/roman/IdeaProjects/university/studentPhotoFiles";

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PassportRepository passportRepository;
    private final CertificateRepository certificateRepository;
    private final DisciplineRepository disciplineRepository;
    private final NotificationRepository notificationRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, AddressRepository addressRepository, PassportRepository passportRepository, CertificateRepository certificateRepository, DisciplineRepository disciplineRepository, NotificationRepository notificationRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;
        this.certificateRepository = certificateRepository;
        this.disciplineRepository = disciplineRepository;
        this.notificationRepository = notificationRepository;
    }



    @Override
    public List<Notification> getNotReadNotification(User authenticatedUser) {
        User user = userRepository.findUserByIdWithStudentOrder(authenticatedUser.getUserId());
        return user.getNotifications().stream()
                .filter(notification -> !notification.isRead())
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> getReadNotification(User authenticatedUser) {
        User user = userRepository.findUserByIdWithStudentOrder(authenticatedUser.getUserId());
        Hibernate.initialize(user.getNotifications());
        return user.getNotifications().stream()
                .filter(Notification::isRead)
                .collect(Collectors.toList());
    }

    @Override
    public void readMessage(User authenticatedUser, String isRead){
        User user = userRepository.findUserByIdWithStudentOrder(authenticatedUser.getUserId());
        List<Notification> notifications = user.getNotifications();
        notifications.stream().forEach(notification ->{
            if(notification.getHeader().equals(isRead)){
                notification.setRead(true);
                notificationRepository.save(notification);
            }
        });
    }

    @Override
    public String checkIsSendedStudentOrder(User user, String facultyName){
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getStudentOrders());
        List<StudentOrder> studentOrders = user.getStudentOrders();
        if (studentOrders == null){
            return "false";
        }
        studentOrders.forEach(studentOrder -> Hibernate.initialize(studentOrder.getFaculty()));
        for (StudentOrder studentOrder : studentOrders){
            if (studentOrder.getFaculty().getFacultyName().equals(facultyName)) {
                return "true";
            }
        }
        return "false";
    }

    @Override
    public StudentPersonalInfo getStudentPersonalInfo(User user, String facultyName){
        Optional<StudentOrder> studentOrder = getStudentOrder(user, facultyName);
        if(studentOrder.isPresent()) {
            Hibernate.initialize(studentOrder.get().getStudentPersonalInfo());
            return studentOrder.get().getStudentPersonalInfo();
        }
       return null;
    }

    @Override
    public void changeStudentPersonalInfo(User user, String facultyName, String firstName, String middleName,
                                     String lastName, String gender, String nationality, LocalDate dateOfBirth){
        StudentPersonalInfo studentPersonalInfo = getStudentPersonalInfo(user, facultyName);

        studentPersonalInfo.setFirstName(firstName);
        studentPersonalInfo.setMiddleName(middleName);
        studentPersonalInfo.setLastName(lastName);
        studentPersonalInfo.setGender(gender);
        studentPersonalInfo.setNationality(nationality);
        studentPersonalInfo.setDateOfBirth(dateOfBirth);
        studentRepository.save(studentPersonalInfo);
    }

    @Override
    public Address getStudentAddress(User user, String facultyName){
       Optional<StudentOrder> studentOrder = getStudentOrder(user, facultyName);
       if(studentOrder.isPresent()){
           Hibernate.initialize(studentOrder.get().getAddress());
           return studentOrder.get().getAddress();
       }
        return null;
    }

    @Override
    public void changeStudentAddressInfo(User user, String facultyName, String city, String street, String building,
                                         String apartment, String postcode){

        Address address = getStudentAddress(user, facultyName);
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setApartment(apartment);
        address.setPostCode(postcode);
        addressRepository.save(address);

    }

    @Override
    public Passport getStudentPassportInfo(User user, String facultyName){
        Optional<StudentOrder> studentOrder = getStudentOrder(user, facultyName);
        if(studentOrder.isPresent()){
            Hibernate.initialize(studentOrder.get().getPassport());
            return studentOrder.get().getPassport() ;
        }
        return null;
    }

    @Override
    public void changeStudentPassportInfo(User user,String facultyName, String passportSeria, String passportNumber,
                                          String registrationOffice, LocalDate issueDate, MultipartFile passportFile){
        Passport passport = getStudentPassportInfo(user, facultyName);
        passport.setPassportSeria(passportSeria);
        passport.setPassportNumber(passportNumber);
        passport.setIssueDate(issueDate);
        passport.setRegistrationOfficeName(registrationOffice);
        File deletedFile = new File(passport.getPassportFilename());
        deletedFile.delete();
        try {
            if (passportFile != null && !passportFile.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(UPLOAD_PATH);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + passportFile.getOriginalFilename();
                passportFile.transferTo(new File(UPLOAD_PATH + "/" + resultFilename));

                passport.setPassportFilename(resultFilename);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        passportRepository.save(passport);
    }

    @Override
    public Certificate getStudentCertificateInfo(User user, String facultyName){
        Optional<StudentOrder> studentOrder = getStudentOrder(user, facultyName);
        if(studentOrder.isPresent()){
            Hibernate.initialize(studentOrder.get().getCertificate());
          return studentOrder.get().getCertificate();
        }
        return null;
    }

    @Override
    public void changeStudentCertificateInfo(User user, String facultyName, String schoolName, String certificateNumber,
                                          LocalDate endSchoolDate, String[] scores, MultipartFile certificateFile){
        Certificate certificate  = getStudentCertificateInfo(user, facultyName);
        certificate.setCertificateNumber(certificateNumber);
        certificate.setSchoolName(schoolName);
        certificate.setEndSchoolDate(endSchoolDate);
        List<Discipline> disciplines = certificate.getDisciplines();
        System.out.println(disciplines);
        List<Integer> scoreList = new ArrayList<>();
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            discipline.setScore(Integer.parseInt(scores[i]));
            discipline.setCertificate(certificate);
            scoreList.add(Integer.parseInt(scores[i]));
            disciplineRepository.save(discipline);

        }
        certificate.setAverageScore(scoreList.stream().collect(Collectors.averagingDouble(Integer::intValue)));
        File file = new File(certificate.getCertificateFilename());
        file.delete();
        try {
            if (certificateFile != null && !certificateFile.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(UPLOAD_PATH);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + certificateFile.getOriginalFilename();
                certificateFile.transferTo(new File(UPLOAD_PATH + "/" + resultFilename));

                certificate.setCertificateFilename(resultFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        certificateRepository.save(certificate);

    }


    public Optional<StudentOrder> getStudentOrder(User authenticatedUser, String facultyName){
        User user = userRepository.findUserByIdWithStudentOrder(authenticatedUser.getUserId());
        List<StudentOrder> studentOrders = user.getStudentOrders();
        studentOrders.forEach(studentOrder -> Hibernate.initialize(studentOrder.getFaculty()));
        Optional<StudentOrder> studentOrder = Optional.empty();
        for (StudentOrder studentOrder1 : studentOrders) {
            if (studentOrder1.getFaculty().getFacultyName().equals(facultyName)) {
                studentOrder = Optional.of(studentOrder1);
                break;
            }
        }
        return studentOrder;
    }

    public List<StudentOrder> getSO(User u){
        User user = userRepository.findUserByIdWithStudentOrder(u.getUserId());
        List<StudentOrder> studentOrders =user.getStudentOrders();
        studentOrders.forEach(studentOrder -> Hibernate.initialize(studentOrder.getFaculty()));
        return studentOrders;
    }



}
