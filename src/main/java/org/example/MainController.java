package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
public class MainController {
    private SalaryRequest request;

    // GET запрос на рассчет
    @GetMapping("/calculate")
    public String calculate(
            @RequestParam double avgSalary,
            @RequestParam int days,
            @RequestParam(required = false) String startDate
    ) {
        try {
            request = new SalaryRequest(avgSalary, days, LocalDate.parse(startDate));
        } catch (Exception e) {
            request = new SalaryRequest(avgSalary, days);
        }

        return String.format("Отпускные составят: %s ",request.calculate());
    }

}