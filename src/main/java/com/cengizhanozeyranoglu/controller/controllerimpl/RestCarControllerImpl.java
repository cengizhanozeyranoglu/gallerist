package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestCarController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoCar;
import com.cengizhanozeyranoglu.dto.DtoCarIU;
import com.cengizhanozeyranoglu.services.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoCar> saveCar(@RequestBody @Valid DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoCar>> getCarList() {
        return ok(carService.getCarList());
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoCar> updateCar(@Valid @PathVariable Long id,@RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.updateCar(id,dtoCarIU));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteCar(@Valid @PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping(path = "/get/{id}")
    @Override
    public RootEntity<DtoCar> getCarById(@Valid @PathVariable Long id) {
        return ok(carService.getCarById(id));
    }
}
