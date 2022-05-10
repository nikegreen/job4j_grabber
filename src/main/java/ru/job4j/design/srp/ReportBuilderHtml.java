package ru.job4j.design.srp;

import java.util.List;

public class ReportBuilderHtml extends BaseReportBuilder {
    @Override
    public String getHeader() {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(System.lineSeparator())
            .append("<html>").append(System.lineSeparator())
            .append("<header>").append(System.lineSeparator())
            .append("</header>").append(System.lineSeparator())
            .append("<body>").append(System.lineSeparator())
            .append("<table border=\"1\">").append(System.lineSeparator())
            .append("<tr>").append(System.lineSeparator())
            .append("<th>Name</th>").append(System.lineSeparator())
            .append("<th>Hired</th>").append(System.lineSeparator())
            .append("<th>Fired</th>").append(System.lineSeparator())
            .append("<th>Salary</th>").append(System.lineSeparator())
            .append("</tr>").append(System.lineSeparator());
        return text.toString();
    }

    @Override
    public String getBodyEmployee(Employee employee) {
        StringBuilder text = new StringBuilder();
        text.append("<tr>").append(System.lineSeparator())
            .append("<td>").append(employee.getName()).append("</td>")
            .append(System.lineSeparator())
            .append("<td>").append(employee.getHired()).append("</td>")
            .append(System.lineSeparator())
            .append("<td>").append(employee.getFired()).append("</td>")
            .append(System.lineSeparator())
            .append("<td>").append(employee.getSalary()).append("</td>")
            .append(System.lineSeparator())
            .append("</tr>").append(System.lineSeparator());
        return text.toString();
    }

    @Override
    public String getFooter() {
        StringBuilder text = new StringBuilder();
        text.append("</table>").append(System.lineSeparator())
            .append("</body>").append(System.lineSeparator())
            .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}
