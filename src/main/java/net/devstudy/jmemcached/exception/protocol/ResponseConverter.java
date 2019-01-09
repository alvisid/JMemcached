package net.devstudy.jmemcached.exception.protocol;


import net.devstudy.jmemcached.exception.protocol.Model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ResponseConverter {
    Response readResponse(InputStream inputStream) throws IOException;
    void writeRequest(OutputStream outputStream, Response response) throws IOException;
}
