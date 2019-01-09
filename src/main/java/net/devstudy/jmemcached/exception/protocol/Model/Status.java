package net.devstudy.jmemcached.exception.protocol.Model;

import net.devstudy.jmemcached.exception.JMemcachedException;

public enum Status {
    ;

    private byte code;
    Status(int code){
        this.code = (byte)code;
    }

    public static Status valueOf(byte byteCode){
        for(Status instance : Status.values()){
            if(instance.getByteCode() == byteCode){
                return instance;
            }
        }
        throw new JMemcachedException("Unsupported byteCode for Status: " + byteCode);
    }
    public byte getByteCode(){
        return code;
    }
}
