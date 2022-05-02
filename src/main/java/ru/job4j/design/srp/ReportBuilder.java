package ru.job4j.design.srp;

public interface ReportBuilder {
    String getHeader();

    String getBody(Employee employee);

    String getFooter();
}
