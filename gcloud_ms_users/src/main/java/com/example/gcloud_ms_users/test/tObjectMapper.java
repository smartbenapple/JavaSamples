package com.example.gcloud_ms_users.test;

import com.fasterxml.jackson.databind.ObjectMapper;

// ObjectMapper() => https://www.baeldung.com/jackson-object-mapper-tutorial
public class tObjectMapper
{
    public <T> String JsonFrom(T object)
    {
        try
        {
            ObjectMapper om = new ObjectMapper();
            //tGameItem gameItem = new tGameItem("T2",2009);
            String result = om.writeValueAsString(object);
            System.out.println("result=" + result);
            return result;
        }
        catch(Exception e)
        {
            System.out.println("[JsonFrom] Error=" + e);
        }
        return "";
    }

    // Issue: Java does not have instantiation of T unknown type.
    /*public <T> T JsonTo(String jsonObject)
    {
        try
        {
            ObjectMapper om = new ObjectMapper();
            om.readValue(jsonObject, tGameItem.class);
        }
        catch(Exception e)
        {

        }
    }*/
}
