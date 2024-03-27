package com.example.gcloud_ms_api.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Simple ObjectMapper wrapper helper class, wrapping the ObjectMapper work.
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
}
