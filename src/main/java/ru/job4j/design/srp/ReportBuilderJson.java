package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ReportBuilderJson implements ReportBuilder {

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getBody(List<Employee> employee) {
        Gson lib = new GsonBuilder().create();
        return lib.toJson(employee);
    }

    @Override
    public String getFooter() {
        return "";
    }
}
