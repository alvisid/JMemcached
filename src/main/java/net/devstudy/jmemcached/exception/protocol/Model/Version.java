package net.devstudy.jmemcached.exception.protocol.Model;

import net.devstudy.jmemcached.exception.JMemcachedException;

public enum Version {
    ;
    private byte high;
    private byte low;

    Version(byte high, byte low) {
        this.high = (byte)(high & 0x7);
        this.low = (byte)(low & 0xF);
    }

    public static Version valueOf(byte byteCode){
        for(Version version : Version.values()){
            if(version.getByteCode() == byteCode){
                return version;
            }
        }
        throw new JMemcachedException("Unsupported byteCode for Version: " + byteCode);
    }

    private byte getByteCode() {
        return (byte)(low + (high << 4));
    }

    @Override
    public String toString(){
        return String.format("%s.%s",high, low);
    }
}
