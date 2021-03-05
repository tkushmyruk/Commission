package ua.taras.kushmyruk;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taras.kushmyruk.model.Address;
import ua.taras.kushmyruk.model.Discipline;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.service.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacultyServiceTest {
    @Autowired
    private FacultyService facultyService;

    private static Faculty faculty;

    @BeforeClass
    public static void createFaculty(){
        faculty = new Faculty();
        faculty.setFacultyName("Math");
        faculty.setFreePlaces(25);
        faculty.setScholarshipPlaces(20);
        faculty.setCommissionIsEnd(false);
        faculty.setCandidates(null);

        Address address = new Address();
        address.setCity("Kiev");
        address.setStreet("Shevchenko");
        address.setBuilding("15b");
        address.setPostCode("22445");
        faculty.setFacultyAddress(address);

        List<Discipline> requiredDisciplines  = new ArrayList<>();
        requiredDisciplines.add(new Discipline("Math"));
        requiredDisciplines.add(new Discipline("Phisic"));
        faculty.setRequiredDisciplines(requiredDisciplines);
    }

    @Test
    public void loadService(){
        Assert.assertNotNull(facultyService);
    }

    @Test
    public void addFacultyTest(){
        facultyService.addFaculty(faculty.getFacultyName(), faculty.getFreePlaces(), faculty.getScholarshipPlaces(),
                faculty.getFacultyAddress().getCity(), faculty.getFacultyAddress().getStreet(), faculty.getFacultyAddress().getBuilding(),
                "math; phisics");
        Faculty facultyFromDb =  facultyService.getFacultyByName(faculty.getFacultyName());
        Assert.assertEquals(faculty.getFreePlaces(), facultyFromDb.getFreePlaces());
    }

    @Test
    public void getAllFacultiesTest(){
        facultyService.addFaculty(faculty.getFacultyName(), faculty.getFreePlaces(), faculty.getScholarshipPlaces(),
                faculty.getFacultyAddress().getCity(), faculty.getFacultyAddress().getStreet(), faculty.getFacultyAddress().getBuilding(),
                "math; phisics");
        List<Faculty> faculties = facultyService.getAllFaculties();
        Assert.assertEquals(1, faculties.size());
    }

    @Test
    public void getFacultyByNameTest(){
        List<Discipline> disciplines = facultyService.getRequiredDisciplines(faculty.getFacultyName());
        Assert.assertEquals(2, disciplines.size());
        Assert.assertEquals("math", disciplines.get(0).getDisciplineName());
    }
    @Test
    public void  changeFacultyTest(){
        Faculty facultyFromDb =  facultyService.getFacultyByName(faculty.getFacultyName());
        List<String> disciplineList = facultyFromDb.getRequiredDisciplines().stream().map(
                discipline -> discipline.getDisciplineName()
        ).collect(Collectors.toList());
        String[] disciplines = new String[disciplineList.size()];
        for (int i = 0; i < disciplineList.size() ; i++) {
            disciplines [i]  = disciplineList.get(i);
        }
        facultyService.redactFaculty(faculty.getFacultyName(), 35, faculty.getScholarshipPlaces(), "",
                faculty.getFacultyAddress().getCity(), faculty.getFacultyAddress().getStreet(), faculty.getFacultyAddress().getBuilding(),
               disciplines );
        Faculty facultyFromDbAfterChange = facultyService.getFacultyByName(faculty.getFacultyName());
        Assert.assertEquals(35, facultyFromDbAfterChange.getFreePlaces());
    }
}
