package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestGalleristCarController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoGalleristCar;
import com.cengizhanozeyranoglu.dto.DtoGalleristCarIU;
import com.cengizhanozeyranoglu.services.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/rest/api/galleristCar")
public class RestGalleristCarImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@RequestBody @Valid DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoGalleristCar>> getGalleristCarList() {
        return ok(galleristCarService.getGalleristCarList());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable Long id, @RequestBody @Valid DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.updateGalleristCar(id, dtoGalleristCarIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteGalleristCar(@Valid @PathVariable Long id) {
        galleristCarService.deleteGalleristCar(id);

    }

    @GetMapping("/get/{id}")
    @Override
    public RootEntity<DtoGalleristCar> getGalleristCarById(@PathVariable @Valid Long id) {
        return ok(galleristCarService.getGalleristCarById(id));
    }
}
