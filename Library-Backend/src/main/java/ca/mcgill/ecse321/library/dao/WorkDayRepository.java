package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.WorkDay;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WorkDayRepository extends CrudRepository<WorkDay, Long> {

    WorkDay findWorkDayById(Long id);

    boolean existsWorkDayById(Long id);

    List<WorkDay> findByDayOfWeek(WorkDay.DayOfWeek dayOfWeek);

}
