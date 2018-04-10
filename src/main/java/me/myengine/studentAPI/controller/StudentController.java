package me.myengine.studentAPI.controller;

import me.myengine.studentAPI.exception.custom.NotFoundException;
import me.myengine.studentAPI.model.Student;
import me.myengine.studentAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class StudentController {

  @Autowired
  private StudentService studentService;

  @RequestMapping(value = "/students", method = RequestMethod.GET)
  public ResponseEntity<List<Student>> getAllStudents() {
    return new ResponseEntity<List<Student>>(this.studentService.getAllStudents(), HttpStatus.OK);
  }

  @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
  public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") long id) {
    return new ResponseEntity<Student>(this.studentService.getStudentById(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/students", method = RequestMethod.POST)
  public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
    return new ResponseEntity<Student>(this.studentService.createStudent(student), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Student>updateStudent(@RequestBody Student student, @PathVariable long id) throws NotFoundException {
    student.setId(id);
    return new ResponseEntity<Student>(this.studentService.updateStudent(id, student), HttpStatus.OK);
  }

  @RequestMapping(value = "/students/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<Student> partialUpdateStudent( @PathVariable long id, @RequestBody Student student) throws NotFoundException {
    student.setId(id);
    return new ResponseEntity<Student>(this.studentService.partialUpdateStudent(id, student), HttpStatus.OK);
  }

  @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) throws NotFoundException {
    this.studentService.deleteStudent(id);
    return new ResponseEntity<HttpStatus>(HttpStatus.OK);
  }
}
