package io.github.ldev22.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class SampleFactory {
    public static SampleBase newSample(String name) throws ReflectionException {
        SampleInfo info = SampleInfos.find(name);
        return info != null ? ClassReflection.newInstance(info.getClazz()) : null;
    }
}
