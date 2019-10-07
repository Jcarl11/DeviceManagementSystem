package com.example.devicemanagementsystem.DB;

import java.util.List;

public interface IOperations<T> {

    T insert(T object);
    int insertAll(List<T> objectList);
    List<T> getAll();
}
