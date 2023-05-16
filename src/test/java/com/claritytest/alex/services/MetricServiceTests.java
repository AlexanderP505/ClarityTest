package com.claritytest.alex.services;

import com.claritytest.alex.dao.MetricRepository;
import com.claritytest.alex.models.Metric;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class MetricServiceTests {

    @Mock
    MetricRepository metricRepository;

    @InjectMocks
    MetricService metricService;

    private final String testSystem = "testSystem";
    private final List<Metric> testMetrics = Arrays.asList(new Metric(1, testSystem, "name 1", 5, 1),
            new Metric(2, testSystem, "name 2", 10, 2));

    @Test
    public void shouldCallRepoAndReturnAllMetricsForSystem_WhenNoFilters() {

        when(metricRepository.findAllBySystem(testSystem)).thenReturn(testMetrics);
        List<Metric> metrics = metricService.findAllMetrics(testSystem, null,null,null);
        assertThat(metrics.size(), is(testMetrics.size()));
        assertTrue(metrics.containsAll(testMetrics));
    }

    @Test
    public void shouldCallRepoAndReturnFilterMetricsForSystemByName_WhenNameSupplied() {

        when(metricRepository.findAllBySystem(testSystem)).thenReturn(testMetrics);
        List<Metric> metrics = metricService.findAllMetrics(testSystem, "name 2",null,null);
        assertThat(metrics.size(), is(1));
        assertEquals("name 2", metrics.get(0).getName());
    }

    //TODO: carry on testing every filter

    //TODO: find by id is enough to verify the repoo is called with the right params

    //TODO: post is enough to verify the repoo is called with the right params

    //TODO: update metric, casses metric found, metric not found , updated with new value, updated with default + 1

    //TODO: metric summarry, finding metrics already tested in above method so only need to test 2 casses metrics found metrics not found
}
