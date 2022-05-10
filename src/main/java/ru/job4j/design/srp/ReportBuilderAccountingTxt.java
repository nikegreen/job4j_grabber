package ru.job4j.design.srp;

import java.util.List;

public class ReportBuilderAccountingTxt extends BaseReportBuilder {
    @Override
    public String getHeader() {
        return "Name; Hired; Fired; Salary(KZT);" + System.lineSeparator();
    }

    @Override
    public String getBodyEmployee(Employee employee) {
        final double k = 6.37;
        StringBuilder text = new StringBuilder();
        text.append(employee.getName()).append(";")
                .append(employee.getHired()).append(";")
                .append(employee.getFired()).append(";")
                .append(employee.getSalary() * k).append(";")
                .append(System.lineSeparator());
        return text.toString();
    }

    @Override
    public String getFooter() {
        return "";
    }
}
