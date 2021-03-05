package ua.taras.kushmyruk.service.impl;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.taras.kushmyruk.controller.FacultyController;
import ua.taras.kushmyruk.model.*;
import ua.taras.kushmyruk.repository.*;
import ua.taras.kushmyruk.service.StudentOrderService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentOrderServiceImpl implements StudentOrderService {
    private final String UPLOAD_PATH = "/Users/roman/IdeaProjects/university/studentPhotoFiles";

    private static final Logger logger = LoggerFactory.getLogger(StudentOrderServiceImpl.class);
    private final FacultyRepository facultyRepository;
    private final StudentOrderRepository studentOrderRepository;
    private final AddressRepository addressRepository;
    private final PassportRepository passportRepository;
    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final DisciplineRepository disciplineRepository;

    private User user;
    private StudentOrder studentOrder;
    private StudentPersonalInfo studentPersonalInfo;
    private Address address;
    private Passport passport;
    private Certificate certificate;
    private Faculty faculty;
    private List<Discipline> disciplines = new ArrayList<>();



    public StudentOrderServiceImpl(FacultyRepository facultyRepository, StudentOrderRepository studentOrderRepository, AddressRepository addressRepository, PassportRepository passportRepository, CertificateRepository certificateRepository, StudentRepository studentRepository, DisciplineRepository disciplineRepository) {
        this.facultyRepository = facultyRepository;
        this.studentOrderRepository = studentOrderRepository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;
        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void addStudentOrder(Long studentId, String facultyName, String educationForm) {
        faculty = facultyRepository.findByFacultyName(facultyName);
        Hibernate.initialize(faculty.getRequiredDisciplines());
        studentOrder = new StudentOrder();
        studentOrder.setIssuDate(LocalDate.now());
        studentOrder.setEducationFrom(EducationFrom.valueOf(educationForm));
        studentOrder.setFaculty(faculty);
    }

    @Override
    public void AddStudentInfo(String firstName, String middleName, String lastName, String nationality,
                               String gender, LocalDate birthDay, User authenticateUser){
        user = authenticateUser;
        studentPersonalInfo = new StudentPersonalInfo();
        studentPersonalInfo.setFirstName(firstName);
        studentPersonalInfo.setMiddleName(middleName);
        studentPersonalInfo.setLastName(lastName);
        studentPersonalInfo.setNationality(nationality);
        studentPersonalInfo.setGender(gender);
        studentPersonalInfo.setDateOfBirth(birthDay);
        studentPersonalInfo.setStudentOrder(studentOrder);
        studentOrder.setUser(user);
    }

    @Override
    public void addAddressInfo(String city, String street, String building, String apartment, String postCode){
        address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setApartment(apartment);
        address.setPostCode(postCode);
        address.setStudentOrder(studentOrder);


    }

    @Override
    public void addPassport(String passportSeria, String passportNumber, String registrationOffice, LocalDate issueDate,
                            MultipartFile passportFile) {
        passport = new Passport();
        passport.setPassportSeria(passportSeria);
        passport.setPassportNumber(passportNumber);
        passport.setRegistrationOfficeName(registrationOffice);
        passport.setIssueDate(issueDate);
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
            passport.setStudentOrder(studentOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCertificate(String schoolName, String certificateNumber,
                               LocalDate endSchoolDate, String[] scores, MultipartFile certificateFile) {
        certificate = new Certificate();
        certificate.setSchoolName(schoolName);
        certificate.setCertificateNumber(certificateNumber);
        certificate.setEndSchoolDate(endSchoolDate);
        List<Discipline> disciplinesFromFaculty = faculty.getRequiredDisciplines();
        List<Integer> scoreList =  new ArrayList<>();
        for (int i = 0; i < disciplinesFromFaculty.size(); i++) {
            Discipline discipline = disciplinesFromFaculty.get(i);
            discipline.setScore(Integer.parseInt(scores[i]));
            discipline.setCertificate(certificate);
            scoreList.add(Integer.parseInt(scores[i]));
            disciplines.add(discipline);
        }
         certificate.setAverageScore(scoreList.stream().collect(Collectors.averagingDouble(Integer::intValue)));
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
        certificate.setStudentOrder(studentOrder);
    }

    @Override
    @Transactional
    public String submitStudentOrder(){
        facultyRepository.save(faculty);
        studentOrderRepository.save(studentOrder);
        studentRepository.save(studentPersonalInfo);
        addressRepository.save(address);
        passportRepository.save(passport);
        certificateRepository.save(certificate);
        disciplines.forEach(disciplineRepository::save);
        return "StudentPersonalInfo order was accepted";
    }



}
