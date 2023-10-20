package com.microservice.course.services;

import com.microservice.course.DTO.StudentDto;
import com.microservice.course.client.StudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) repository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        repository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentByIdCourse(Long idCourse){
        //CONSULTAR CURSO
        Course course = repository.findById(idCourse).orElse(new Course());
        //OBTENER LOS ESTUDIANTES
        List<StudentDto> studentDtoList =  studentClient.findStudentByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDtoList(studentDtoList).build();
    }

}
