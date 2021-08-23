package com.rsjavasolutions.sortingpagination.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final int recordCount;
    private final T response;
}
