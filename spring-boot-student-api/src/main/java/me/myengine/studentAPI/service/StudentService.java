package me.myengine.studentAPI.service;

import me.myengine.studentAPI.exception.custom.NotFoundException;
import me.myengine.studentAPI.model.Student;
import me.myengine.studentAPI.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getAllStudents() {
    return this.studentRepository.findAll();
  }

  public Student getStudentById(long id) {
    return this.studentRepository.findOne(id);
  }

  public Student createStudent(Student student) {
    return this.studentRepository.save(student);
  }

  public void deleteStudent(long id) throws NotFoundException {
    Student dbStudent = this.getStudentById(id);
    if (dbStudent == null) {
      throw new NotFoundException("Student with id " + id + " does not exist.");
    }
    this.studentRepository.delete(id);
  }

  public Student updateStudent(long id, Student student) throws NotFoundException {
    Student dbStudent = this.getStudentById(id);
    if (dbStudent == null) {
      throw new NotFoundException("Student with id " + id + " does not exist.");
    }

    return this.studentRepository.save(student);
  }

  public Student partialUpdateStudent(long id, Student queryStudent) throws NotFoundException {

    Student dbStudent = this.getStudentById(id);
    if (dbStudent == null) {
      throw new NotFoundException("Student with id " + id + " does not exist.");
    }

    BeanUtils.copyProperties(queryStudent, dbStudent, StudentService.getNullPropertyNamesToIgnore(queryStudent));
    return this.updateStudent(id, dbStudent);
  }

  public static String[] getNullPropertyNamesToIgnore(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
      // check if value of this property os null then add it to the collection
      Object srcValue = src.getPropertyValue(propertyDescriptor.getName());
      if (srcValue == null) {
        emptyNames.add(propertyDescriptor.getName());
      }
    }

    String[] result = new String[emptyNames.size()];

    return emptyNames.toArray(result);
  }



}
