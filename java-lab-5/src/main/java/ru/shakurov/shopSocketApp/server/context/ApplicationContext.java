package ru.shakurov.shopSocketApp.server.context;

public interface ApplicationContext {
    <T extends Component> T getComponent(Class<T> componentType, String componentName);
}
