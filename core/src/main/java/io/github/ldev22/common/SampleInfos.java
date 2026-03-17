package io.github.ldev22.common;

import java.util.Arrays;
import java.util.List;

import io.github.ldev22.samples.*;

public class SampleInfos {
    public static final List<SampleInfo> allSamples = Arrays.asList(
        new SampleInfo(ApplicationListenerSample.class),
        new SampleInfo(InputListeningSample.class),
        new SampleInfo(InputPollingSample.class),
        new SampleInfo(ModuleInfoSample.class),
        new SampleInfo(MultiplexerSample.class),
        new SampleInfo(ReflectionSample.class),
        new SampleInfo(OrthographicCameraSample.class),
        new SampleInfo((ViewportSample.class)),
        new SampleInfo(SpriteBatchSample.class)
    );

    public static String[] getSamplesNames() {
        return allSamples.stream()
            .map(SampleInfo::getName)
            .sorted()
            .toArray(String[]::new);
    }

    public static SampleInfo find(String name) {
        return allSamples.stream()
            .filter(s -> s.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
}
