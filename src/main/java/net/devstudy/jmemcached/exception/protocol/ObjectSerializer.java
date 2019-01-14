package net.devstudy.jmemcached.exception.protocol;

public interface ObjectSerializer {
    byte[] toByteArray(Object object);

    Object fromByteArray(byte[] array);
}
