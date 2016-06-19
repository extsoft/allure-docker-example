package com.extsoft.allure.docker.example.tests;

import org.junit.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class AllPassedTests2 {

    @Test
    public void test1() {
        attach("1");
    }

    @Title("Second test")
    @Description("Second test description")
    @Features("second feature")
    @Severity(SeverityLevel.TRIVIAL)
    @TestCaseId("123")
    @Test
    public void test2() {
        attach("second");
        step1();
        step2();
        throw new RuntimeException();
    }

    @Attachment(type = "text/plain", value = "Very important")
    public String attach(String s) {
        return s;
    }

    @Attachment
    public String attach2(String s) {
        return s;
    }

    @Step
    public void step1() {
        attach2("step attach");
        System.out.println("ddd");
    }

    @Step
    public void step2() {
        System.out.println("step2");
    }
}
