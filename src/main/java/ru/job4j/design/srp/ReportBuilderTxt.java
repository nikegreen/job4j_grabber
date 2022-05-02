package ru.job4j.design.srp;

public class ReportBuilderTxt implements ReportBuilder {
    @Override
    public String getHeader() {
        return "Name; Hired; Fired; Salary;" + System.lineSeparator();
    }

    @Override
    public String getBody(Employee employee) {
        StringBuilder text = new StringBuilder();
        text.append(employee.getName()).append(";")
                .append(employee.getHired()).append(";")
                .append(employee.getFired()).append(";")
                .append(employee.getSalary()).append(";")
                .append(System.lineSeparator());
        return text.toString();
    }

    @Override
    public String getFooter() {
        return "";
    }
}
