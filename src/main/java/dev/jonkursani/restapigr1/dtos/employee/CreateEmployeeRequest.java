package dev.jonkursani.restapigr1.dtos.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name should not exceed {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name should not exceed {max} characters")
    private String lastName;

    private int departmentId;

    private LocalDate hireDate;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email should not exceed {max} characters")
    @Email(message = "Email should be valid")
    private String email;
}
