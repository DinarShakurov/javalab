package ru.shakurov.shopSocketApp.server.protocol;

import ru.shakurov.shopSocketApp.server.dto.Dto;

public interface Response {
    <E extends Dto>void setData(E data);
}
