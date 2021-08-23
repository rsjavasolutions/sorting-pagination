package com.rsjavasolutions.sortingpagination.car.service;

import com.rsjavasolutions.sortingpagination.car.CarEntity;
import com.rsjavasolutions.sortingpagination.car.CarRepository;
import com.rsjavasolutions.sortingpagination.car.exception.CarNotFoundException;
import com.rsjavasolutions.sortingpagination.car.mapper.CarMapper;
import com.rsjavasolutions.sortingpagination.car.request.CarRequest;
import com.rsjavasolutions.sortingpagination.car.response.CarResponse;
import com.rsjavasolutions.sortingpagination.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rsjavasolutions.sortingpagination.car.mapper.CarMapper.mapToEntity;
import static com.rsjavasolutions.sortingpagination.car.mapper.CarMapper.mapToResponse;


@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public List<CarResponse> getSortedCars(Sort.Direction direction, String field) {
        return carRepository.findAll(Sort.by(direction, field))
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CarResponse> getPaginatedCars(int pageNumber, int pageSize) {
        return carRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ApiResponse<List<CarResponse>> getSortedAndPaginatedCars(int pageNumber, int pageSize, Sort.Direction direction, String field) {
        List<CarResponse> cars = carRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, field))
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());

        return new ApiResponse<>(cars.size(), cars);
    }

    @Transactional
    public CarResponse getCar(String uuid) {
        CarEntity carEntity = carRepository.findByUuid(uuid).orElseThrow(() -> new CarNotFoundException(uuid));
        return mapToResponse(carEntity);
    }

    @Transactional
    public String saveCar(CarRequest request) {
        log.debug("Save car request with params: {}", request);

        return carRepository.save(mapToEntity(request)).getUuid();
    }

    @Transactional
    public CarResponse updateCar(String uuid, CarRequest request) {
        CarEntity carEntity = carRepository.findByUuid(uuid).orElseThrow(() -> new CarNotFoundException(uuid));

        carEntity.setBrand(request.getBrand());
        carEntity.setModel(request.getModel());

        return mapToResponse(carEntity);
    }

    @Transactional
    public void deleteCar(String uuid) {
        carRepository.deleteByUuid(uuid);
    }

    @PostConstruct
    public void prepareTestData() {
        Set<CarEntity> carsToSave = new HashSet<>();

        for (int i = 0; i < 231; i++) {
            carsToSave.add(new CarEntity(
                    "Ford" + i,
                    "Mustang" + i));
        }

        carRepository.saveAll(carsToSave);
    }
}
