package ru.shakurov.shopSocketApp.server.context;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ApplicationContextBasedOnReflection implements ApplicationContext {
    private Map<String, Component> componentMap;

    public ApplicationContextBasedOnReflection() {
        this.componentMap = new HashMap<String, Component>();
        init();
    }

    private void init() {
        Reflections reflections = new Reflections("ru.shakurov.shopSocketApp");
        Set<Class<? extends Component>> subTypes = reflections.getSubTypesOf(Component.class);
        for (Class<? extends Component> subType : subTypes) {
            try {
                Component component = subType.getConstructor().newInstance();
                componentMap.put(component.getName(), component);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
        }
        try {
            for (Component component : componentMap.values()) {
                for (Field field : component.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    for (Component component1 : componentMap.values()) {
                        if (field.getType().isAssignableFrom(component1.getClass())) {
                            field.set(component, component1);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public <T extends Component> T getComponent(Class<T> componentType, String componentName) {
        return (T) componentMap.get(componentName);
    }
}
