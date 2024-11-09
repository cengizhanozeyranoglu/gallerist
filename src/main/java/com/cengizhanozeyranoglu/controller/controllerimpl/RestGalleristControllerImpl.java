package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestGalleristController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoGallerist;
import com.cengizhanozeyranoglu.dto.DtoGalleristIU;
import com.cengizhanozeyranoglu.services.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping(path = "save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@RequestBody @Valid DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoGallerist>> getGalleristList() {
        return ok(galleristService.getGalleristList());
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoGallerist> updateGallerist(@Valid @PathVariable Long id, @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.updateGallerist(id, dtoGalleristIU));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteGallerist(@Valid @PathVariable Long id) {
        galleristService.deleteGallerist(id);

    }

    @GetMapping(path = "/get/{id}")
    @Override
    public RootEntity<DtoGallerist> getGalleristById(@Valid @PathVariable Long id) {
        return ok(galleristService.getGalleristById(id));
    }
}
