package com.example.gcloud_ms_api.communication;

import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Connect
{
    //ArrayList<String> registry = new ArrayList<>();
    Dictionary<String, HttpServletResponse> registry = new Hashtable<>();

    public void Register(String message, HttpServletResponse response)
    {
        registry.get("bob");
    }
}
