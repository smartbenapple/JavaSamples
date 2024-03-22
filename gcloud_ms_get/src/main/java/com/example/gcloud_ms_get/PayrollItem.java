package com.example.gcloud_ms_get;

import com.fasterxml.jackson.annotation.JsonView;

public class PayrollItem
{
    public interface JsonType {};

    private int _checkNumber = 5;
    private double _net = 3560.34;
    private double _gross = 7980.23;

    public int get_checkNumber()
    {
        return _checkNumber;
    }

    // Use JsonView to control output for deserialization.
    // https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/jackson.html
    @JsonView(JsonType.class)
    public double get_net()
    {
        return _net;
    }

    // Use JsonView to control output for deserialization.
    // https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/jackson.html
    @JsonView(JsonType.class)
    public double get_gross()
    {
        return _gross;
    }
}
