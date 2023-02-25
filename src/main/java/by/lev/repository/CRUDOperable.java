package by.lev.repository;

import by.lev.app_exception.AppException;

import java.util.List;

public interface CRUDOperable<T, N> {

    void create(T t) throws AppException;

    T read(N value) throws AppException;

    List<T> readAll() throws AppException;

    void delete(N value) throws AppException;

}
