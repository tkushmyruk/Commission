package ua.taras.kushmyruk.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "e_certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certificate_id")
    private Long id;
    @Column(name = "certificate_number")
    private String certificateNumber;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "average_score")
    private double averageScore;
    @Column(name = "end_school_date")
    private LocalDate endSchoolDate;
    @Column(name = "certificate_filename")
    private String certificateFilename;
    @OneToMany(mappedBy = "certificate")
    private List<Discipline> disciplines;
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "student_order_id")
    private StudentOrder studentOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public LocalDate getEndSchoolDate() {
        return endSchoolDate;
    }

    public void setEndSchoolDate(LocalDate endSchoolDate) {
        this.endSchoolDate = endSchoolDate;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public StudentOrder getStudentOrder() {
        return studentOrder;
    }

    public void setStudentOrder(StudentOrder studentOrder) {
        this.studentOrder = studentOrder;
    }

    public String getCertificateFilename() {
        return certificateFilename;
    }

    public void setCertificateFilename(String certificateFilename) {
        this.certificateFilename = certificateFilename;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
