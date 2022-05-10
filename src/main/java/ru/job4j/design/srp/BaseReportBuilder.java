package ru.job4j.design.srp;

import java.util.List;

public abstract class BaseReportBuilder implements ReportBuilder {
    @Override
    public String getBody(List<Employee> employees) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee: employees) {
            sb.append(getBodyEmployee(employee));
        }
        return sb.toString();
    }

    public abstract String getBodyEmployee(Employee employee);
}