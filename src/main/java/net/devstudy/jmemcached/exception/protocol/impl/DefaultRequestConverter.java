package net.devstudy.jmemcached.exception.protocol.impl;

import net.devstudy.jmemcached.exception.protocol.Model.Command;
import net.devstudy.jmemcached.exception.protocol.Model.Request;
import net.devstudy.jmemcached.exception.protocol.RequestConverter;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DefaultRequestConverter extends AbstractPackageConverter implements RequestConverter {

    @Override
    public Request readRequest(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        checkProtocolVersion(dataInputStream.readByte());
        byte cmd = dataInputStream.readByte();
        byte flags = dataInputStream.readByte();
        boolean hasKey = (flags & 1) != 0;
        boolean hasTTL = (flags & 2) != 0;
        boolean hasData = (flags & 4) != 0;

        return readRequest(cmd, hasKey, hasTTL, hasData, dataInputStream);
    }

    protected Request readRequest(byte cmd, boolean hasKey, boolean hasTTL, boolean hasData, DataInputStream dataInputStream) throws IOException {
        Request request = new Request(Command.valueOf(cmd));
        if(hasKey){
            byte keyLength = dataInputStream.readByte();
            byte[] keyBytes = IOUtils.readFully(dataInputStream,keyLength);
            request.setKey(new String(keyBytes, StandardCharsets.US_ASCII));
        }
        if(hasTTL){
            request.setTtl(dataInputStream.readLong());
        }
        if(hasData){
            int dataLength = dataInputStream.readInt();
            request.setData(IOUtils.readFully(dataInputStream, dataLength));
        }
        return request;
    }

    @Override
    public void writeRequest(OutputStream outputStream, Request request) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(getVersionByte());
        dataOutputStream.writeByte(request.getCommand().getByteCode());

    }
    protected byte getFlagsByte(Request request){
        byte flags = 0;
        if(request.hasKey()){
            flags |= 1;
        }
        if (request.hasTtl()){
            flags |= 2;
        }
        if (request.hasData()){
            flags |= 4;
        }
        return flags;
    }
}
