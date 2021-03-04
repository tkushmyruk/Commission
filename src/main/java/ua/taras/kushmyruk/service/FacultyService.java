package ua.taras.kushmyruk.service;

import ua.taras.kushmyruk.model.Discipline;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.model.StudentOrder;
import ua.taras.kushmyruk.model.StudentPersonalInfo;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculties();

    Faculty getFacultyByName(String facultyName);

    List<Discipline> getRequiredDisciplines(String facultyName);

    void addFaculty(String name, int freePlaces, int scholarshipPlaces,
                    String city, String street, String building, String disciplines);

    void redactFaculty(String facultyName, int freePlaces, int scholarshipPlaces, String disciplines,
                       String city, String street, String building, String[] redactedDisciplines);

    List<StudentOrder> getFacultyStudentList(String facultyName);

    List<Faculty> getAllSortedFaculties(String sorting);
}
