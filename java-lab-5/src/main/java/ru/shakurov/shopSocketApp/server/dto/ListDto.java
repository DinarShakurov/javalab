package ru.shakurov.shopSocketApp.server.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDto<T> implements Dto {
    private List<T> list;

    public ListDto(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public ListDto<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public static <T, R> ListDto<R> from(List<T> list, Function<T, R> mapper) {
        return new ListDto<>(list.stream()
                .map(mapper)
                .collect(Collectors.toList()));
    }
}
