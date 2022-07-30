package com.example.interface_javafx;

import java.util.stream.IntStream;
public interface ConvBlockToPixel {
    default int[] convToPixel(int row, int rectSize) {
        return IntStream.range(0, row).map(e -> e * rectSize).toArray();
    }
}