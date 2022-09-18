package ru.cft.shiftlab.gorin.testtask.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.RecordNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.service.AddProductService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/market/add")
public class AddProductController {
    private final AddProductService addProductService;

    @Autowired
    public AddProductController(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    @PostMapping("/monitor")
    public ResponseEntity<?> addMonitor(@RequestBody MonitorFeaturesDTO monitorFeaturesDTO) {
        MonitorEntity savedMonitor;
        try {
            savedMonitor = addProductService.saveMonitor(monitorFeaturesDTO).get();
        }
        catch (NoSuchElementException ex) {
            throw new NoSuchElementException();
        }
        long id = savedMonitor.getId();
        StringBuilder response = new StringBuilder("The id of saved monitor is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/hdd")
    public ResponseEntity<?> addHdd(@RequestBody HddFeaturesDTO hddFeaturesDTO) {
        HddEntity savedHdd;
        try {
            savedHdd = addProductService.saveHdd(hddFeaturesDTO).get();
        }
        catch (NoSuchElementException ex) {
            throw new NoSuchElementException();
        }
        long id = savedHdd.getId();
        StringBuilder response = new StringBuilder("The id of saved hdd is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/laptop")
    public ResponseEntity<?> addLaptop(@RequestBody LaptopFeaturesDTO laptopFeaturesDTO) {
        LaptopEntity savedLaptop;
        try {
            savedLaptop = addProductService.saveLaptop(laptopFeaturesDTO).get();
        }
        catch (NoSuchElementException ex) {
            throw new NoSuchElementException();
        }
        long id = savedLaptop.getId();
        StringBuilder response = new StringBuilder("The id of saved laptop is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pc")
    public ResponseEntity<?> addPc(@RequestBody PcFeaturesDTO pcFeaturesDTO) {
        PcEntity savedPc;
        try {
            savedPc = addProductService.savePc(pcFeaturesDTO).get();
        }
        catch (NoSuchElementException ex) {
            throw new NoSuchElementException();
        }
        long id = savedPc.getId();
        StringBuilder response = new StringBuilder("The id of saved pc is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(NoSuchElementException exception) {
        String savingRecordExceptionMessage = "Something went wrong during the saving the new record. Check input parameters\n";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage + exception.getMessage());
    }
}
