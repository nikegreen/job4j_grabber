package ru.job4j.design.srp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderTxt();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, reportBuilder, null);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    /**
     * for programmer version
     */
    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderHtml();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, reportBuilder, null);
        StringBuilder expect = new StringBuilder();
        expect.append("<!DOCTYPE html>").append(System.lineSeparator())
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
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getHired()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getFired()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    /**
     * for HR version
     */
    @Test
    public void whenHrGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderHrTxt();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 110);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store,
                reportBuilder,
                Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName)
                        .reversed()
        );
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        String out = engine.generate(em -> true);
        assertThat(out , is(expect.toString()));
    }

    /**
     * for accounting version
     */
    @Test
    public void whenAccountingGenerated() {
        final double k = 6.37;
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderAccountingTxt();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 110);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store,
                reportBuilder,
                null
        );
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary(KZT);")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()*k).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary() * k).append(";")
                .append(System.lineSeparator());
        String out = engine.generate(em -> true);
        assertThat(out , is(expect.toString()));
    }
}