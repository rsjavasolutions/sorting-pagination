package com.rsjavasolutions.sortingpagination.car;


import com.rsjavasolutions.sortingpagination.car.request.CarRequest;
import com.rsjavasolutions.sortingpagination.car.response.CarResponse;
import com.rsjavasolutions.sortingpagination.car.service.CarService;
import com.rsjavasolutions.sortingpagination.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {

    private static final String DIRECTION_ASC = "ASC";
    private static final String ID_FIELD = "id";
    private static final String PAGE_NUMBER = "0";
    private static final String PAGE_SIZE = "10";

    private final CarService carService;

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCar(@PathVariable String uuid) {
        return carService.getCar(uuid);
    }

    @GetMapping("sorted")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCars(
            @RequestParam(value = "direction", required = false, defaultValue = DIRECTION_ASC) Sort.Direction direction,
            @RequestParam(value = "field", required = false, defaultValue = ID_FIELD) String field) {

        return carService.getSortedCars(direction, field);
    }

    @GetMapping("paginated")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCars(
            @RequestParam(value = "pageNumber", required = false, defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE) int pageSize) {

        return carService.getPaginatedCars(pageNumber, pageSize);
    }

    @GetMapping("sorted-paginated")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<CarResponse>> getCars(
            @RequestParam(value = "direction", required = false, defaultValue = DIRECTION_ASC) Sort.Direction direction,
            @RequestParam(value = "field", required = false, defaultValue = ID_FIELD) String field,
            @RequestParam(value = "pageNumber", required = false, defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE) int pageSize) {

        return carService.getSortedAndPaginatedCars(pageNumber, pageSize, direction, field);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveCar(@RequestBody CarRequest request) {
        return carService.saveCar(request);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse updateCar(@PathVariable String uuid, @RequestBody CarRequest request) {
        return carService.updateCar(uuid, request);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable String uuid) {
        carService.deleteCar(uuid);
    }
}
