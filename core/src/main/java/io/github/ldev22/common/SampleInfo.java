package io.github.ldev22.common;

public class SampleInfo {
    private final Class<? extends SampleBase> clazz;
    private final String name;

    public SampleInfo(Class<? extends SampleBase> clazz) {
        this.clazz = clazz;
        this.name = clazz.getSimpleName();
    }

    public Class<? extends SampleBase> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }


}
