package io.github.ldev22.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class SampleFactory {
    public static SampleBase newSample(String name) {
        SampleInfo info = SampleInfos.find(name);
        try {
            return info != null ? ClassReflection.newInstance(info.getClazz()) : null;
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
