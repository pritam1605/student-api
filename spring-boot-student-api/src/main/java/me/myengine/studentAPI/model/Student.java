package me.myengine.studentAPI.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name") private String firstName;
  @Column(name = "middle_name") private String middleName;
  @Column(name = "last_name") private String lastName;
  @Column(name = "program") private String program;

  public Student() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", middleName='" + middleName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", program='" + program + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return id == student.id &&
      Objects.equals(firstName, student.firstName) &&
      Objects.equals(middleName, student.middleName) &&
      Objects.equals(lastName, student.lastName) &&
      Objects.equals(program, student.program);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, middleName, lastName, program);
  }
}
