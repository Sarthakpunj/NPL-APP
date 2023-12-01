package com.example.nplfinal;

import java.nio.ByteBuffer;

public class NTPStats extends NTPPacket {
    private long destTimestamp;

    // Constructors, getters, and setters omitted for brevity

    public void setDestTimestamp(long destTimestamp) {
        this.destTimestamp = destTimestamp;
    }
}

