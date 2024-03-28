package com.example.gcloud_ms_api.utility;

import java.util.function.Consumer;

public class ThreadHelper
{
    public static <T> void Async(Consumer<T> work, T input)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                work.accept(input);
            }
        }).start();
    }
}
