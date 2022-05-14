package ru.job4j.design.srp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderTxt();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, reportBuilder, null);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertEquals(engine.generate(em -> true), expect);
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
        String expect = "<!DOCTYPE html>" + System.lineSeparator()
                + "<html>" + System.lineSeparator()
                + "<header>" + System.lineSeparator()
                + "</header>" + System.lineSeparator()
                + "<body>" + System.lineSeparator()
                + "<table border=\"1\">" + System.lineSeparator()
                + "<tr>" + System.lineSeparator()
                + "<th>Name</th>" + System.lineSeparator()
                + "<th>Hired</th>" + System.lineSeparator()
                + "<th>Fired</th>" + System.lineSeparator()
                + "<th>Salary</th>" + System.lineSeparator()
                + "</tr>" + System.lineSeparator()
                + "<tr>" + System.lineSeparator()
                + "<td>" + worker.getName() + "</td>"
                + System.lineSeparator()
                + "<td>" + worker.getHired() + "</td>"
                + System.lineSeparator()
                + "<td>" + worker.getFired() + "</td>"
                + System.lineSeparator()
                + "<td>" + worker.getSalary() + "</td>"
                + System.lineSeparator()
                + "</tr>" + System.lineSeparator()
                + "</table>" + System.lineSeparator()
                + "</body>" + System.lineSeparator()
                + "</html>" + System.lineSeparator();
        assertEquals(engine.generate(em -> true), expect);
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
        String expect = "Name; Salary;"
                + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getSalary() + ";"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        String out = engine.generate(em -> true);
        assertEquals(out, expect);
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
        String expect = "Name; Hired; Fired; Salary(KZT);"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() * k + ";"
                + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getHired() + ";"
                + worker2.getFired() + ";"
                + worker2.getSalary() * k + ";"
                + System.lineSeparator();
        String out = engine.generate(em -> true);
        assertEquals(out, expect);
    }

    /**
     * for XML version
     */
    @Test
    public void whenXmlGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderXml();
        Calendar now = new GregorianCalendar(2021, Calendar.APRIL, 1);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 110);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store,
                reportBuilder,
                null
        );
        final ThreadLocal<DateFormat> DATE_FORMAT
                = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X"));
        String date = DATE_FORMAT.get().format(now.getTime());
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + System.lineSeparator()
                + "<employees>" + System.lineSeparator()
                + "    <employee name=\"Ivan\" hired=\"" + date + "\" "
                + "fired=\"" + date + "\" salary=\"100.0\"/>" + System.lineSeparator()
                + "    <employee name=\"Petr\" hired=\"" + date + "\" "
                + "fired=\"" + date + "\" salary=\"110.0\"/>" + System.lineSeparator()
                + "</employees>" + System.lineSeparator();
        String out = engine.generate(em -> true);
        assertEquals(out, expect);
    }

    /**
     * for JSON version
     */
    @Test
    public void whenJsonGenerated() {
        MemStore store = new MemStore();
        ReportBuilder reportBuilder = new ReportBuilderJson();
        Calendar now = new GregorianCalendar(2021, Calendar.APRIL, 1);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 110);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store,
                reportBuilder,
                null
        );
        String expect = "["
        + "{\"name\":\"Ivan\","
        + "\"hired\":{\"year\":2021,\"month\":3,\"dayOfMonth\":1,"
        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},"
        + "\"fired\":{\"year\":2021,\"month\":3,\"dayOfMonth\":1,"
        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},"
        + "\"salary\":100.0},"
        + "{\"name\":\"Petr\","
        + "\"hired\":{\"year\":2021,\"month\":3,\"dayOfMonth\":1,"
        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},"
        + "\"fired\":{\"year\":2021,\"month\":3,\"dayOfMonth\":1,"
        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},"
        + "\"salary\":110.0}]";
        String out = engine.generate(em -> true);
        assertEquals(out, expect);
    }
}