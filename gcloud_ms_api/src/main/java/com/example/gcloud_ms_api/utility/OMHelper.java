package com.example.gcloud_ms_api.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Simple ObjectMapper wrapper helper class, wrapping the ObjectMapper work.
// https://www.baeldung.com/jackson-object-mapper-tutorial
public class OMHelper
{
    public static <T> String Parse(T type)
    {
        try
        {
            ObjectMapper om = new ObjectMapper();
            String result = om.writeValueAsString(type);
            return result;
        }
        catch(JsonProcessingException jsonE)
        {
            System.out.println("API:[OMHelper.Parse] Error=" + jsonE.getMessage());
        }
        return "failure!";
    }

    public static <T> T Convert(String json, Class<? extends Object> T)
    {
        try
        {
            ObjectMapper om = new ObjectMapper();
            T result = (T) om.readValue(json, T);
            return result;
        }
        catch(JsonProcessingException jsonE)
        {
            System.out.println("API:[OMHelper.Convert] Error=" + jsonE.getMessage());
        }
        return null;
    }
}
