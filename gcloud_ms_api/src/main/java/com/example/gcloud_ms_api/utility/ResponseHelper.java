package com.example.gcloud_ms_api.utility;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;

public class ResponseHelper
{
    public static void Write(HttpServletResponse response, String message)
    {
        try
        {
            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            OutputStream output = response.getOutputStream();
            byte[] outputBytes = message.getBytes();
            output.write(outputBytes);
        }
        catch(Exception e)
        {
            System.out.println("API:[ResponseHelper.Write] Error=" + e.getMessage());
        }
    }

    public static void Write(ServletResponse response, String message)
    {
        try
        {
            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            OutputStream output = response.getOutputStream();
            byte[] outputBytes = message.getBytes();
            output.write(outputBytes);
        }
        catch(Exception e)
        {
            System.out.println("API:[ResponseHelper.Write] Error=" + e.getMessage());
        }
    }

    public static <T extends Class> void Write(HttpServletResponse response, T object)
    {
        try
        {
            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            OutputStream output = response.getOutputStream();
            byte[] outputBytes = BytesHelper.Convert(object);
            output.write(outputBytes);
        }
        catch(Exception e)
        {
            System.out.println("API:[ResponseHelper.Write] Error=" + e.getMessage());
        }
    }
}
