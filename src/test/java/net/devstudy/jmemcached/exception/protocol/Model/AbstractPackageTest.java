package net.devstudy.jmemcached.exception.protocol.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractPackageTest {

    private static AbstractPackage newInstance(byte[] array){
        return new AbstractPackage(array){};
    }

    @Test
    public void hasDataNull() {
        AbstractPackage apackege = newInstance(null);
        assertFalse(apackege.hasData());
    }
    @Test
    public void hasDataEmpty(){
        AbstractPackage apackage = newInstance(new byte[0]);
        assertFalse(apackage.hasData());
    }

    @Test
    public void hasData(){
        AbstractPackage apackage = newInstance(new byte[]{1, 2, 3});
        assertTrue(apackage.hasData());
    }
}