package ca.devpro.saskpolytech;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
	public List<Student> findAllByGraduationYear(Integer graduationYear);
}
