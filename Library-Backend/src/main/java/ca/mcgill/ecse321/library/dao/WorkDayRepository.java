package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.WorkDay;
import org.springframework.data.repository.CrudRepository;

public interface WorkDayRepository extends CrudRepository<WorkDay, String> {

    WorkDay findWorkDayById(Long id);

    boolean existsWorkDayById(Long id);

}
