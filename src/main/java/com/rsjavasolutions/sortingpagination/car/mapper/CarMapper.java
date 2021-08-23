package com.rsjavasolutions.sortingpagination.car.mapper;


import com.rsjavasolutions.sortingpagination.car.CarEntity;
import com.rsjavasolutions.sortingpagination.car.request.CarRequest;
import com.rsjavasolutions.sortingpagination.car.response.CarResponse;

public class CarMapper {

    public static CarEntity mapToEntity(CarRequest request) {
        return new CarEntity(
                request.getBrand(),
                request.getModel());
    }

    public static CarResponse mapToResponse(CarEntity entity) {
        return new CarResponse(
                entity.getUuid(),
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getCreationDateTime()
        );
    }
}
