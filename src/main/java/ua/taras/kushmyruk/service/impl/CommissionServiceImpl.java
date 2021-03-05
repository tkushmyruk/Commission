package ua.taras.kushmyruk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.taras.kushmyruk.controller.FacultyController;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.model.Notification;
import ua.taras.kushmyruk.model.StudentOrder;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.repository.FacultyRepository;
import ua.taras.kushmyruk.repository.NotificationRepository;
import ua.taras.kushmyruk.repository.StudentOrderRepository;
import ua.taras.kushmyruk.service.CommissionService;
import ua.taras.kushmyruk.service.FacultyService;

import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    private static final Logger logger = LoggerFactory.getLogger(CommissionServiceImpl.class);
    private final FacultyService facultyService;
    private final NotificationRepository notificationRepository;
    private final StudentOrderRepository studentOrderRepository;
    private final FacultyRepository facultyRepository;

    public CommissionServiceImpl(FacultyService facultyService,
                                 NotificationRepository notificationRepository,
                                 StudentOrderRepository studentOrderRepository, FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.notificationRepository = notificationRepository;
        this.studentOrderRepository = studentOrderRepository;
        this.facultyRepository = facultyRepository;
    }

   @Override
   @Transactional
    public void acceptStudents(String facultyName){
      List<StudentOrder> studentOrders =  facultyService.getFacultyStudentList(facultyName);
      Faculty faculty = facultyService.getFacultyByName(facultyName);
        for (int i = 0; i < studentOrders.size(); i++) {
            if(i < faculty.getFreePlaces()) {
                StudentOrder studentOrder = studentOrders.get(i);
                studentOrder.setAccepted(true);
                studentOrderRepository.save(studentOrder);
                sendAcceptanceMessage(studentOrders.get(i), facultyName);
            }
            else {
                sendNotAcceptanceMessage(studentOrders.get(i), facultyName);
            }
        }
        faculty.setCommissionIsEnd(true);
        facultyRepository.save(faculty);
        logger.info("Student was successfully accepted to :{}", facultyName);

    }

    @Override
    public List<StudentOrder> getAcceptedStudentOrder(String facultyName){
        Faculty faculty = facultyService.getFacultyByName(facultyName);
        return studentOrderRepository.findByAcceptedStudentOrders(faculty.getId());
    }

   @Transactional
    private void sendAcceptanceMessage(StudentOrder studentOrder, String facultyName){
        Notification notification = new Notification();
        notification.setHeader("Your acceptance on " + facultyName);
        notification.setText("Your invite on accession to " + facultyName + "was successful." +
                "Please wait message with instructions");
        notification.setRead(false);
        User user = studentOrder.getUser();
        notification.setUser(user);
        notificationRepository.save(notification);
    }

    @Transactional
    private void sendNotAcceptanceMessage(StudentOrder studentOrder, String facultyName){
        Notification notification = new Notification();
        notification.setHeader("Your acceptance on " + facultyName);
        notification.setText("Your invite on accession to " + facultyName + "was failed." +
                "Please try again in next year");
        notification.setRead(false);
        User user = studentOrder.getUser();
        notification.setUser(user);
        notificationRepository.save(notification);
    }
}
