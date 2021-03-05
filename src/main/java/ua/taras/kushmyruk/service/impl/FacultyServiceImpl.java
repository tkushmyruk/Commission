package ua.taras.kushmyruk.service.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.taras.kushmyruk.model.*;
import ua.taras.kushmyruk.repository.*;
import ua.taras.kushmyruk.service.FacultyService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final AddressRepository addressRepository;
    private final DisciplineRepository disciplineRepository;


    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              AddressRepository addressRepository,
                              DisciplineRepository disciplineRepository) {
        this.facultyRepository = facultyRepository;
        this.addressRepository = addressRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyByName(String facultyName){
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        return faculty ;
    }

    @Override
    public List<Discipline> getRequiredDisciplines(String facultyName) {
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        return  faculty.getRequiredDisciplines();
    }

    @Override
    @Transactional
    public void addFaculty(String name, int freePlaces, int scholarshipPlaces,
                           String city, String street, String building, String disciplines) {
        Faculty facultyFromDb =  facultyRepository.findByFacultyName(name);
        if(facultyFromDb != null){
            return;
        }
        Faculty faculty = new Faculty();
        faculty.setFacultyName(name);
        faculty.setFreePlaces(freePlaces);
        faculty.setScholarshipPlaces(scholarshipPlaces);
        facultyRepository.save(faculty);
        Arrays.stream(disciplines.split(";"))
                .distinct()
                .forEach(s -> {
                    Discipline disciplineFromBd = disciplineRepository.findByDisciplineName(s.trim());
                    if(disciplineFromBd == null) {
                        Discipline discipline = new Discipline();
                        discipline.setDisciplineName(s.trim());
                        discipline.setFaculty(faculty);
                        disciplineRepository.save(discipline);
                    }
                });
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setFaculty(faculty);
        addressRepository.save(address);
    }

    @Override
    public void redactFaculty(String facultyName, int freePlaces, int scholarshipPlaces, String disciplines,
                              String city, String street, String building, String [] redactedDisciplines) {
        Faculty faculty  = facultyRepository.findByFacultyName(facultyName);
        faculty.setFreePlaces(freePlaces);
        faculty.setScholarshipPlaces(scholarshipPlaces);
        facultyRepository.save(faculty);
        List<Discipline> disciplineList = faculty.getRequiredDisciplines();
        for (int i = 0; i <redactedDisciplines.length ; i++) {
           Discipline discipline = disciplineList.get(i);
           discipline.setDisciplineName(redactedDisciplines[i]);
           disciplineRepository.save(discipline);
        }
        Arrays.stream(disciplines.split(";"))
                .distinct()
                .forEach(s -> {
                    Discipline disciplineFromBd = disciplineRepository.findByDisciplineName(s.trim());
                    if(disciplineFromBd == null) {
                        Discipline discipline = new Discipline();
                        discipline.setDisciplineName(s.trim());
                        discipline.setFaculty(faculty);
                        disciplineRepository.save(discipline);
                    }
                });
        Address address = faculty.getFacultyAddress();
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);

        addressRepository.save(address);
    }

    @Override
    public List<StudentOrder> getFacultyStudentList(String facultyName){
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        List<StudentOrder> studentOrders = faculty.getCandidates();
       studentOrders = studentOrders.stream()
                .sorted(Comparator.comparingDouble(studentOrder -> studentOrder.getCertificate().getAverageScore()))
                .collect(Collectors.toList());
        return studentOrders;
    }

    @Override
    public List<Faculty> getAllSortedFaculties(String sorting) {
        if(sorting == null){
            return facultyRepository.findAll();
        }
       if(!sorting.equals("places")){
           return facultyRepository.findByOrderByFacultyNameAsc();
       }

       return facultyRepository.findByOrderByFreePlacesAsc();
    }
}
