package ru.job4j.design.srp;

import java.util.List;

public interface ReportBuilder {
    String getHeader();

    String getBody(List<Employee> employee);

    String getFooter();
}
