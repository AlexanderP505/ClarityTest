package com.claritytest.alex.services;

import com.claritytest.alex.dao.MetricRepository;
import com.claritytest.alex.exceptions.ResourceNotFoundException;
import com.claritytest.alex.models.Metric;
import com.claritytest.alex.models.MetricSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricService {

    MetricRepository metricRepository;

    @Autowired
    public MetricService(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    public List<Metric> findAllMetrics(String system, String name, Integer from, Integer to) {
        List<Metric> metrics = metricRepository.findAllBySystem(system);
        if (CollectionUtils.isEmpty(metrics)) {
            return Collections.EMPTY_LIST;
        }
        return metrics.stream()
                .filter(metric -> StringUtils.isEmpty(name) || metric.getName().equals(name))
                .filter(metric -> from == null || metric.getDate() > from)
                .filter(metric -> to == null || metric.getDate() < from)
                .collect(Collectors.toList());
    }

    public Metric findMetricById(Integer id) {
        return metricRepository.findAllById(id);
    }

    public Metric postMetric(Metric metric) {
        return metricRepository.save(metric);
    }


    public Metric updateMetric(Integer id, Metric metric) {
        Metric currentMetric = findMetricById(id);
        if (currentMetric == null) {
            throw new ResourceNotFoundException();
        }
        Metric.MetricBuilder metricBuilder = metric.toBuilder();
        metricBuilder.id(id);

        if (metric.getValue() == null) {
            metricBuilder.value(currentMetric.getValue() + 1);
        }
        return metricRepository.save(metricBuilder.build());
    }

    public MetricSummary createMetricSummary(String system, String name, Integer from, Integer to) {
        List<Metric> metrics = findAllMetrics(system, name, from, to);
        MetricSummary.MetricSummaryBuilder summary = MetricSummary.builder()
                .system(system)
                .from(from)
                .to(to);
        if (CollectionUtils.isEmpty(metrics)) {
            return summary
                    .value(0)
                    .build();
        }
        return summary.value(
                        metrics.stream()
                                .map(Metric::getValue)
                                .reduce(0, Integer::sum))
                .build();
    }
}
