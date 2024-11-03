package com.cengizhanozeyranoglu.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RootEntity<T> {

    private boolean result;

    private T data;

    private String errorMessage;

    public static <T> RootEntity<T> ok(T data) {

        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setData(data);
        rootEntity.setResult(true);
        rootEntity.setErrorMessage(null);
        return rootEntity;
    }

    public static <T> RootEntity<T> error(String errorMessage) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setErrorMessage(errorMessage);
        rootEntity.setResult(false);
        rootEntity.setData(null);
        return rootEntity;
    }
}
