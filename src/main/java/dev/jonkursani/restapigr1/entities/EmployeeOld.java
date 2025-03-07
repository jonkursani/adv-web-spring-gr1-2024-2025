//package dev.jonkursani.restapigr1.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "employees")
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Integer id;
//
//    @NotNull
//    @Size(max = 50)
//    @Column(name = "first_name", nullable = false, length = 50)
//    private String firstName;
//
//    @NotNull
//    @Size(max = 50)
//    @Column(name = "last_name", nullable = false, length = 50)
//    private String lastName;
//
//    // foreign key depatment_id
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @Column(name = "hire_date")
//    private LocalDate hireDate;
//
//    @Column(unique = true)
//    private String email;
//}
