package dev.jonkursani.restapigr1.schedulers;

import dev.jonkursani.restapigr1.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//        ┌───────────── second (0-59)
//        │ ┌───────────── minute (0 - 59)
//        │ │ ┌───────────── hour (0 - 23)
//        │ │ │ ┌───────────── day of the month (1 - 31)
//        │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
//        │ │ │ │ │ ┌───────────── day of the week (0 - 7)
//        │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
//        │ │ │ │ │ │
//        * * * * * *

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeReportScheduler {
    private final EmployeeRepository repository;

    // https://docs.spring.io/spring-framework/reference/integration/scheduling.html#scheduling-cron-expression
    // https://crontab.guru/

    // every monday 9 AM
//    @Scheduled(cron = "0 0 9 ? * MON")
//    @Scheduled(fixedRate = 10000)
    public void checkAvailableEmployees() {
        log.info("Generating employee report");

        var employees = repository.findAll();

        employees.forEach(employee -> {
            log.info("Employee: {} {}", employee.getFirstName(), employee.getLastName());
        });

        log.info("Employee report generated: {}", LocalDate.now());
    }

    // every friday 8 AM
//    @Scheduled(cron = "0 0 8 ? * FRI")
    @Scheduled(fixedRate = 10000)
    @Transactional
    public void getHireReport() {
        log.info("Generating hire report");

        var oneMonthAgo = LocalDate.now().minusMonths(1);
        var employees = repository.findAll();

        employees.stream()
                .filter(
                employee -> employee.getHireDate() != null && employee.getHireDate().isAfter(oneMonthAgo))
                .forEach(employee -> {
                    log.info("Employee: {} {} hired on {} | Department {}",
                    employee.getFirstName(), employee.getLastName(), employee.getHireDate()
                            , employee.getDepartment() != null ? employee.getDepartment().getName() : "N/A");
                });

        log.info("Hire report generated: {}", LocalDate.now());
    }
}