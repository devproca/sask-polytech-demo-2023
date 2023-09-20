package ca.devpro.saskpolytech;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class Student {
	@Id
	private Long studentId;
	private String name;
	private Integer graduationYear;
}
