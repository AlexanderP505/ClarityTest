package com.claritytest.alex.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MetricSummary {

    private String system;
    private int from;
    private int to;
    private int value;

}
