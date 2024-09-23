package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SalaryRequest {
    // указываемая зп
    double avgSalary;
    // длительность отпуска
    int vacationDays;
    // дата ухода в отпуск
    LocalDate startDate;
    // среднее кол-во дней в месяце
    final double AVG = 29.3;

    // праздники
    private final List<LocalDate> holidays = List.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 12, 31)
    );

    // запрос с датой
    SalaryRequest(double avg_salary, int vacation_days, LocalDate startDate) {
        this.avgSalary = avg_salary;
        this.vacationDays = vacation_days;
        this.startDate = startDate;
    }

    // обычный с датой
    SalaryRequest(double avg_salary, int vacation_days) {
        this.avgSalary = avg_salary;
        this.vacationDays = vacation_days;
    }

    protected String calculate() {
        if (startDate != null) {
            return calculateAdvanced();
        } else {
            return String.format("%s" ,calculateSimple());
        }
    }

    private double calculateSimple() {
        return ((avgSalary / AVG) * vacationDays);
    }

    private String calculateAdvanced() {
        int currentDays = 0;
        LocalDate currentDate = startDate;
        while (currentDays < vacationDays) {
            if (isWorkingDay(currentDate)) {
                currentDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return String.format("%s , Дата возвращения %s", calculateSimple(), currentDate);
    }

    // проверка на выходной
    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return !(holidays.contains(date) || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }
}
