package ua.taras.kushmyruk.service;

import ua.taras.kushmyruk.model.StudentOrder;

import java.util.List;

public interface CommissionService {
    void acceptStudents(String facultyName);

    List<StudentOrder> getAcceptedStudentOrder(String facultyName);
}
