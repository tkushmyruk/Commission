package ua.taras.kushmyruk;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taras.kushmyruk.model.*;
import ua.taras.kushmyruk.service.StudentOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentOrderTest {
    @Autowired
    private StudentOrderService studentOrderService;

    private static StudentOrder studentOrder;

    @BeforeClass
    public static void createStudentOrder(){
        studentOrder = new StudentOrder();
        studentOrder.setEducationFrom(EducationFrom.DAY);
        studentOrder.setIssuDate(LocalDate.now());
        studentOrder.setAccepted(false);

        Address address = new Address();
        address.setCity("kiev");
        address.setStreet("shevchenko");
        address.setBuilding("22A");
        address.setApartment("224");
        address.setPostCode("12335");
        studentOrder.setAddress(address);

        Faculty faculty = new Faculty();
        faculty.setFacultyName("Math");
        faculty.setFreePlaces(22);
        faculty.setScholarshipPlaces(15);
        studentOrder.setFaculty(faculty);

        Passport passport = new Passport();
        passport.setPassportSeria("TT");
        passport.setPassportNumber("222444");
        passport.setIssueDate(LocalDate.parse("2012-02-12"));
        passport.setRegistrationOfficeName("Shevchenkivskiy RVK");
        studentOrder.setPassport(passport);

        Certificate certificate = new Certificate();
        certificate.setSchoolName("School number 1");
        certificate.setCertificateNumber("12345678");

        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(new Discipline("math", 160));
        disciplines.add(new Discipline("phisic", 187));
        certificate.setDisciplines(disciplines);

        studentOrder.setCertificate(certificate);

        StudentPersonalInfo studentPersonalInfo = new StudentPersonalInfo();
        studentPersonalInfo.setFirstName("Vasiliy");
        studentPersonalInfo.setMiddleName("Petrovich");
        studentPersonalInfo.setLastName("Maruskin");
        studentPersonalInfo.setNationality("Ukranian");
        studentPersonalInfo.setDateOfBirth(LocalDate.parse("1994-10-15"));
        studentPersonalInfo.setGender("Male");

        studentOrder.setStudentPersonalInfo(studentPersonalInfo);
    }

    @Test
    public void loadService(){
        Assert.assertNotNull(studentOrderService);
    }
}
