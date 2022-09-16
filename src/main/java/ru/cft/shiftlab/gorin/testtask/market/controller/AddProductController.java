package ru.cft.shiftlab.gorin.testtask.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.service.AddProductService;

@RestController
@RequestMapping("/market/add")
public class AddProductController {
    private AddProductService addProductService;

    @Autowired
    public AddProductController(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    @PostMapping("/monitor")
    public ResponseEntity<?> addMonitor(@RequestBody MonitorFeaturesDTO monitorFeaturesDTO) {
        long id = addProductService.saveMonitor(monitorFeaturesDTO);
        StringBuilder response = new StringBuilder("The id of saved monitor is: " + id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/hdd")
    public ResponseEntity<?> addHdd(@RequestBody HddFeaturesDTO hddFeaturesDTO) {
        long id = addProductService.saveHdd(hddFeaturesDTO);
        StringBuilder response = new StringBuilder("The id of saved hdd is: " + id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/laptop")
    public ResponseEntity<?> addLaptop(@RequestBody LaptopFeaturesDTO laptopFeaturesDTO) {
        long id = addProductService.saveLaptop(laptopFeaturesDTO);
        StringBuilder response = new StringBuilder("The id of saved laptop is: " + id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/pc")
    public ResponseEntity<?> addPc(@RequestBody PcFeaturesDTO pcFeaturesDTO) {
        long id = addProductService.savePc(pcFeaturesDTO);
        StringBuilder response = new StringBuilder("The id of saved hdd is: " + id);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
