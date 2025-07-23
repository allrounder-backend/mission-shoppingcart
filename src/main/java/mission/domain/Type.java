package mission.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Type {
    private static final Map<String, Type> TYPES = new HashMap<>();
    private final String name;

    private Type(String name) {
        this.name = name;
    }

    public static Type createType(String name) {
        return TYPES.computeIfAbsent(name, Type::new);
    }

    public static boolean exists(String name) {
        return TYPES.containsKey(name);
    }

    public String getName() {
        return name;
    }

    public boolean isType(String name) {
        return this.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
