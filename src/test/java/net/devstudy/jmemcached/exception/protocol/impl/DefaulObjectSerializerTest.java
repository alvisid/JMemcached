package net.devstudy.jmemcached.exception.protocol.impl;

import net.devstudy.jmemcached.exception.JMemcachedException;
import net.devstudy.jmemcached.exception.protocol.impl.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;

public class DefaulObjectSerializerTest {

    private final DefaulObjectSerializer defaulObjectSerializer = new DefaulObjectSerializer();

    private final Object testObject = "Test";

    private final byte[] testObjectArray = {-84, -19, 0, 5, 116, 0, 4, 84, 101, 115, 116};

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toByteArraySuccess(){
        byte[] actual = defaulObjectSerializer.toByteArray(testObject);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    public void toByteArrayNull(){
        assertNull(defaulObjectSerializer.toByteArray(null));
    }

    @Test
    public void toByteArraySerializableException(){
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Test java.lang.object should implement java.io.Serializable interface"));
        defaulObjectSerializer.toByteArray(new Object());
    }

    @Test
    public void toByteArrayIOException(){
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Can't convert object to byte array: Write IO"));
        thrown.expectCause(isA(IOException.class));
//        defaulObjectSerializer.toByteArray(new SeriazableFailedClass);
    }

    @Test
    public void fromByteArraySuccess(){
        String actual = (String) DefaulObjectSerializer.fromByteArray(testObjectArray);
        assertEquals(testObject, actual);
    }

    @Test
    public void fromByteArrayNull(){
        assertNull(defaulObjectSerializer.fromByteArray(null));
    }

    @Test
    public void toByteArr(){
//        byte[] actual = defaulObjectSerializer.toByteArray(new SeriazableFailedClass());
//        System.out.println(Arrays.toString(actual));
    }



}