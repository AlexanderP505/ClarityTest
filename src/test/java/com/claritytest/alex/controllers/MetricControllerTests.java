package com.claritytest.alex.controllers;

import com.claritytest.alex.services.MetricService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MetricControllerTests {

    @Mock
    private MetricService metricService;

    @InjectMocks
    private MetricController controller;

    @Test
    public void getMetricsShouldCallServiceWithCorrectValues() {
        String system = "testSystem";
        String name = "testNAme";
        Integer from = 5;
        Integer to = 10;

        controller.getMetrics(system, name, from, to);
        verify(metricService).findAllMetrics(eq(system),eq(name),eq(from),eq(to));
    }

    //@TODO: this should be tested by http calls instead of calling the controller like this. Things to test missing none optional params, check 400s are returned. the 404 on the put call

}
