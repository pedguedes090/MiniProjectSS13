package rikkei.edu.miniprojectss13.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "employee")
public class Employee {

    private Long id;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;
}
