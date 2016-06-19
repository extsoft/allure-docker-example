package com.extsoft.allure.docker.example;

import org.junit.runner.JUnitCore;
import ru.yandex.qatools.allure.junit.AllureRunListener;

public class App {

    public static void main(String[] args) {
        System.setProperty("allure.results.directory", "allure-result");
        TestClassRepository testClassRepository = new TestClassRepository("^com.extsoft.allure.docker.example.tests.+");
        JUnitCore junit = new JUnitCore();
        junit.addListener(new AllureRunListener());
        junit.run(testClassRepository.loadClasses());
    }
}
