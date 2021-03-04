package ua.taras.kushmyruk.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "e_faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "faculty_id")
    private Long id;
    @Column(name = "faculty_name")
    private String facultyName;
    @Column(name = "free_places")
    private int freePlaces;
    @Column(name = "scholarship_places")
    private int scholarshipPlaces;
    @Column(name = "commission_is_end")
    private boolean commissionIsEnd;
    @OneToOne(cascade = {CascadeType.REFRESH}, mappedBy = "faculty")
    private Address facultyAddress;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "university_name")
    private University university;
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "faculty")
    private List<Discipline> requiredDisciplines;
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "faculty",fetch = FetchType.EAGER)
    private List<StudentOrder> candidates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public int getScholarshipPlaces() {
        return scholarshipPlaces;
    }

    public void setScholarshipPlaces(int scholarshipPlaces) {
        this.scholarshipPlaces = scholarshipPlaces;
    }

    public Address getFacultyAddress() {
        return facultyAddress;
    }

    public void setFacultyAddress(Address facultyAddress) {
        this.facultyAddress = facultyAddress;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Discipline> getRequiredDisciplines() {
        return requiredDisciplines;
    }

    public void setRequiredDisciplines(List<Discipline> requiredDisciplines) {
        this.requiredDisciplines = requiredDisciplines;
    }

    public List<StudentOrder> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<StudentOrder> candidates) {
        this.candidates = candidates;
    }

    public boolean isCommissionIsEnd() {
        return commissionIsEnd;
    }

    public void setCommissionIsEnd(boolean commissionIsEnd) {
        this.commissionIsEnd = commissionIsEnd;
    }
}
