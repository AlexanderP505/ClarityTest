package com.claritytest.alex.dao;

import com.claritytest.alex.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Integer> {

    List<Metric> findAll();
    Metric findAllById(int id);
    List<Metric> findAllBySystem(String system);
}
