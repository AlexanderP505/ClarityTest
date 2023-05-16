package com.claritytest.alex.controllers;

import com.claritytest.alex.models.Metric;
import com.claritytest.alex.models.MetricSummary;
import com.claritytest.alex.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetricController {

    private final MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    List<Metric> getMetrics(@RequestParam String system,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) Integer from,
                            @RequestParam(required = false) Integer to) {

        return metricService.findAllMetrics(system,name,from,to);
    }

    @RequestMapping(value = "/metrics/{id}", method = RequestMethod.GET)
    Metric getMetricById(@RequestParam Integer id) {
        return metricService.findMetricById(id);
    }

    @RequestMapping(value = "/metrics", method = RequestMethod.POST)
    Metric postMetrics(@RequestBody Metric metric) {
        return metricService.postMetric(metric);
    }

    @RequestMapping(value = "/metrics/{id}", method = RequestMethod.PUT)
    Metric putMetric(@PathVariable Integer id, @RequestBody Metric metric) {
       return metricService.updateMetric(id, metric);
    }

    @RequestMapping(value = "/metricSummary", method = RequestMethod.GET)
    MetricSummary getMetricSummary(@RequestParam String system,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) Integer from,
                                   @RequestParam(required = false) Integer to){
        return metricService.createMetricSummary(system, name, from, to);
    }

}
