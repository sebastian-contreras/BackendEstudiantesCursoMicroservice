package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    ICourseService service;
 @PostMapping("/create")
 @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Course course){
     service.save(course);
 }
 @GetMapping("/all")
    public ResponseEntity<?> findAll(){
     return ResponseEntity.ok(service.findAll());
 }
 @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
     return ResponseEntity.ok(service.findById(id));
 }

 @GetMapping("/search-student/{idCourse}")
public ResponseEntity<?> findStudentByCourse(@PathVariable Long idCourse){
     return ResponseEntity.ok(service.findStudentByIdCourse(idCourse));
 }
}
