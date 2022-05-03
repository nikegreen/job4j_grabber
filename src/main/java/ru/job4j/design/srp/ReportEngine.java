package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;

    private final ReportBuilder reportBuilder;

    private final Comparator<Employee> comparator;

    public ReportEngine(Store store, ReportBuilder reportBuilder, Comparator<Employee> comparator) {
        this.store = store;
        this.reportBuilder = reportBuilder;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(reportBuilder.getHeader());
        List<Employee> list = store.findBy(filter);
        if (comparator != null) {
            list.sort(comparator);
        }
        for (Employee employee : list) {
            text.append(reportBuilder.getBody(employee));
        }
        text.append(reportBuilder.getFooter());
        return text.toString();
    }
}