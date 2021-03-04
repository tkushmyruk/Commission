package ua.taras.kushmyruk.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "e_univeristy")
public class University {
    @Id
    @Column(name = "university_name")
    private String universityName;
    @Column(name = "university_porfile")
    private String univeristyProfile;
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "university")
    private List<Faculty> faculties;
    @OneToOne(cascade = {CascadeType.REFRESH}, mappedBy = "university")
    private Address mainHull;


}
