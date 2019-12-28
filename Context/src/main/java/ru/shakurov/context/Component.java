package ru.shakurov.context;

public interface Component {
    String getName();

    default void saveContext(ApplicationContext context) {

    }
}
