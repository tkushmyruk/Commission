package ua.taras.kushmyruk.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "e_student_order")
public class StudentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_order_id")
    private Long studentOrderId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "is_accepted")
    private boolean isAccepted;
    @OneToOne(cascade =  CascadeType.REFRESH, mappedBy = "studentOrder")
    private StudentPersonalInfo studentPersonalInfo;
    @OneToOne(cascade =  CascadeType.REFRESH, mappedBy = "studentOrder")
    private Passport passport;
    @OneToOne(cascade =  CascadeType.REFRESH, mappedBy = "studentOrder")
    private Address address;
    @OneToOne(cascade =  CascadeType.REFRESH, mappedBy = "studentOrder")
    private Certificate certificate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Enumerated
    @Column(name = "education_form")
    private EducationFrom educationFrom;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(Long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public LocalDate getIssuDate() {
        return issueDate;
    }

    public void setIssuDate(LocalDate issuDate) {
        this.issueDate = issuDate;
    }

    public StudentPersonalInfo getStudentPersonalInfo() {
        return studentPersonalInfo;
    }

    public void setStudentPersonalInfo(StudentPersonalInfo studentPersonalInfo) {
        this.studentPersonalInfo = studentPersonalInfo;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public EducationFrom getEducationFrom() {
        return educationFrom;
    }

    public void setEducationFrom(EducationFrom educationFrom) {
        this.educationFrom = educationFrom;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}


