package com.example.gcloud_ms_api.utility;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// How to output to a class to bytes array in Java
// https://www.tutorialspoint.com/How-to-convert-an-object-to-byte-array-in-java
// > Make class implement a Serializable interface.
// > Create a Java's ByteArrayOutputStream object.
// > Create a Java's ObjectOutputStream object.
// > Use the oos.writeObject() to add the class into the stream.
// > Class flush() to push the block of bytes into the internal stream.
// > Then call the toBytesArray() to retrieve the bytes[] array.
public class BytesHelper
{
    // todo: test - does extends on generics work as a kinda constraint mechanism.
    public static <T extends Serializable> byte[] Convert(T object)
    {
        try
        {
            ByteArrayOutputStream baou = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baou);
            oos.writeObject(object);
            oos.flush();
            byte[] bytes = baou.toByteArray();
            return bytes;
        }
        catch(Exception e)
        {
            System.out.println("API:[BytesHelper.Convert] Error="+ e.getMessage());
        }
        return null;
    }
}
