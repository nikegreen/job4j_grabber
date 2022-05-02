package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;

    private final ReportBuilder reportBuilder;

    public ReportEngine(Store store, ReportBuilder reportBuilder) {
        this.store = store;
        this.reportBuilder = reportBuilder;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(reportBuilder.getHeader());
        for (Employee employee : store.findBy(filter)) {
            text.append(reportBuilder.getBody(employee));
        }
        text.append(reportBuilder.getFooter());
        return text.toString();
    }
}