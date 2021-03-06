package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.WorkDayRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.model.WorkDay.DayOfWeek;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadLibrarianService {

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    WorkDayRepository workdayRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public HeadLibrarian createHeadLibrarian(String aUsername, String aPassword, String aAddress) {
        checkValidUsername(aUsername);
        UserService.checkValidPassword(aPassword);
        UserService.checkValidAddress(aAddress);

        HeadLibrarian librarian = new HeadLibrarian();
        librarian.setUsername(aUsername);
        librarian.setPassword(aPassword);
        librarian.setAddress(aAddress);

        headLibrarianRepository.save(librarian);

        return librarian;
    }

    /**
     * @param username
     * @param newPassword
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public HeadLibrarian changeHeadLibrarianPassword(String username, String newPassword) {
        HeadLibrarian librarian = headLibrarianRepository.findHeadLibrarianByUsername(username);

        if (librarian == null) {
            throw new IllegalArgumentException("Head librarian is not found.");
        }

        if (UserService.checkValidPassword(newPassword)) {
            librarian.setPassword(newPassword);
        }
        headLibrarianRepository.save(librarian);
        return librarian;
    }

    /**
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public boolean deleteHeadLibrarian(String username) {
        HeadLibrarian librarian = getHeadLibrarian(username);
        if (librarian == null) {
            throw new IllegalArgumentException("Head librarian is not found.");
        }
        headLibrarianRepository.delete(librarian);
        return true;
    }

    /**
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public HeadLibrarian getHeadLibrarian(String username) {
        return headLibrarianRepository.findHeadLibrarianByUsername(username);
    }

    //list of all head librarians
    @Transactional
    public List<HeadLibrarian> getAllHeadLibrarians() {
        return Services.toList(headLibrarianRepository.findAll());
    }

    @Transactional
    public List<Librarian> getAllLibrarians() {
        return Services.toList(librarianRepository.findAll());
    }

    @Transactional
    public void assignSchedule(String startTime, String endTime, String librarianUsername){
        for(DayOfWeek day: DayOfWeek.values()){
            assignDaySchedule(startTime, endTime, librarianUsername, day);
        }
    }

    private void assignDaySchedule(String startTime, String endTime, String librarianUsername, DayOfWeek day){
        if(librarianRepository.existsLibrarianByUsername(librarianUsername)){
            librarianRepository.findLibrarianByUsername(librarianUsername).setWorkHours(WeekSchedule(Schedule(day, Time.valueOf(startTime), Time.valueOf(endTime))));
        }
    }

    /**
     * HeadLib can assign schedule
     *
     * @param workday
     * @param startTime
     * @param endTime
     * @param librarianUser
     * @param headlibUser
     * @return assigned schedule
     * @author saghar sahebi
     */
    @Transactional
    public Set<WorkDay> AssignScheduleLibrarian(DayOfWeek workday, Time startTime, Time endTime, String librarianUser, String headlibUser) {


        if (getAllHeadLibrarians().contains(headLibrarianRepository.findHeadLibrarianByUsername(headlibUser))) {
            librarianRepository.findLibrarianByUsername(librarianUser).setWorkHours(WeekSchedule(Schedule(workday, startTime, endTime)));
        } else {
            throw new IllegalArgumentException("Only a headlibrarian can assign schedules.");
        }

        workdayRepository.saveAll(librarianRepository.findLibrarianByUsername(librarianUser).getWorkHours());

        return librarianRepository.findLibrarianByUsername(librarianUser).getWorkHours();

    }


    public Set<WorkDay> AssignScheduleHeadLibrarian(DayOfWeek workday, Time startTime, Time endTime, String headlibUser) {

        if (getAllHeadLibrarians().contains(headLibrarianRepository.findHeadLibrarianByUsername(headlibUser))) {
            getHeadLibrarian(headlibUser).setWorkHours(WeekSchedule(Schedule(workday, startTime, endTime)));
        } else {
            throw new IllegalArgumentException("Only a headlibrarian can assign schedules.");
        }

        workdayRepository.saveAll(getHeadLibrarian(headlibUser).getWorkHours());

        return getHeadLibrarian(headlibUser).getWorkHours();


    }


    /**
     * HeadLib can delete a schedule
     *
     * @param workday
     * @param startTime
     * @param endTime
     * @param librarianUser
     * @param headlibUser
     * @return assigned schedule
     * @author saghar sahebi
     */
    @Transactional
    public boolean DeleteSchedule(DayOfWeek workday, Time startTime, Time endTime, String librarianUser, String headlibUser) {
        Set<WorkDay> headlibrarianschedule = AssignScheduleHeadLibrarian(workday, startTime, endTime, headlibUser);
        Set<WorkDay> librarianschedule = AssignScheduleLibrarian(workday, startTime, endTime, librarianUser, headlibUser);
        if (headlibrarianschedule.isEmpty() || librarianschedule.isEmpty()) {
            throw new IllegalArgumentException("No schedule exists for this librarian.");
        }

        if (getAllHeadLibrarians().contains(headLibrarianRepository.findHeadLibrarianByUsername(headlibUser))) {
            throw new IllegalArgumentException("Only a headlibrarian can assign schedules.");
        } else {
            workdayRepository.deleteAll(librarianRepository.findLibrarianByUsername(librarianUser).getWorkHours());
            return true;
        }


    }

    @Transactional
    public WorkDay Schedule(DayOfWeek workday, Time astartTime, Time aendTime) {
        WorkDay workdays = new WorkDay();
        workdays.setDayOfWeek(workday);
        workdays.setStartTime(astartTime);
        workdays.setEndTime(aendTime);
        return workdays;

    }

    @Transactional
    public Set<WorkDay> WeekSchedule(WorkDay aWorkDaySchedule) {
        Set<WorkDay> theSchedule = new HashSet<>();
        theSchedule.add(aWorkDaySchedule);
        return theSchedule;
    }

    /**
     * @param username
     * @return
     * @author Jewoo Lee
     */
    public boolean checkValidUsername(String username) {
        if (username == null || username == "") {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (headLibrarianRepository.findHeadLibrarianByUsername(username) == null) {
            return true;
        }
        throw new IllegalArgumentException("Username already exists.");
    }

    /**
     * @param username
     * @param password
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public HeadLibrarian login(String username, String password) {
        if (headLibrarianRepository.findHeadLibrarianByUsername(username) == null) {
            throw new IllegalArgumentException("Invalid Username.");
        }

        HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByUsername(username);
        if (headLibrarian != null && headLibrarian.getPassword().equals(password)) {
            return headLibrarian;
        }

        throw new IllegalArgumentException("Incorrect Password.");
    }
}
