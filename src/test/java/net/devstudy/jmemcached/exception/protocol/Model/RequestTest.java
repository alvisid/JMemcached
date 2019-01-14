package net.devstudy.jmemcached.exception.protocol.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestTest {

    private Request request;

    @Before
    public void before() {
        request = new Request(Command.CLEAR);
    }

    @Test
    public void hasKeyTrue() {
        request.setKey("key");
        assertTrue(request.hasKey());
    }

    @Test
    public void hasKeyFalse() {
        assertFalse(request.hasKey());
    }

}