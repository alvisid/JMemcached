package net.devstudy.jmemcached.exception.protocol.impl;

import net.devstudy.jmemcached.exception.JMemcachedException;
import net.devstudy.jmemcached.exception.protocol.Model.Version;

public class AbstractPackageConverter {
    protected void checkProtocolVersion(byte versionByte) {
        Version version = Version.valueOf(versionByte);
        if (version != Version.VERSION_1_0) {
            throw new JMemcachedException("Usupported protocol version: " + version);
        }
    }

    protected byte getVersionByte() {
        return Version.VERSION_1_0.getByteCode();
    }
}
