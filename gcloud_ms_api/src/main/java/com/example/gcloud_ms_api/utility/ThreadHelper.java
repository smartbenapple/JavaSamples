package com.example.gcloud_ms_api.utility;

import java.util.function.Consumer;

// Threads
// https://www.w3schools.com/java/java_threads.asp
// https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
public class ThreadHelper
{
    // Java: Use Consumer as function callback
    // Consumer<String> work = (name) ->
    // {
    //      ... Add Code Here ...
    // };
    //
    //  // Create Message
    //  ThreadHelper.Async(work, null);
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
