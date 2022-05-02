package ru.job4j.design.srp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderTxt();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, reportBuilder);
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

    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderHtml();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, reportBuilder);
        StringBuilder expect = new StringBuilder();
        expect.append("<html>").append(System.lineSeparator())
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
}