package ru.cft.shiftlab.gorin.testtask.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.SavingRecordException;
import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.service.AddProductService;


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
        MonitorEntity savedMonitor = addProductService.saveMonitor(monitorFeaturesDTO);
        if (savedMonitor == null) {
            throw new SavingRecordException();
        }
        long id = savedMonitor.getId();
        StringBuilder response = new StringBuilder("The id of saved monitor is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/hdd")
    public ResponseEntity<?> addHdd(@RequestBody HddFeaturesDTO hddFeaturesDTO) {
        HddEntity savedHdd = addProductService.saveHdd(hddFeaturesDTO);
        if (savedHdd == null) {
            throw new SavingRecordException();
        }
        long id = savedHdd.getId();
        StringBuilder response = new StringBuilder("The id of saved hdd is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/laptop")
    public ResponseEntity<?> addLaptop(@RequestBody LaptopFeaturesDTO laptopFeaturesDTO) {
        LaptopEntity savedLaptop = addProductService.saveLaptop(laptopFeaturesDTO);
        if (savedLaptop == null) {
            throw new SavingRecordException();
        }
        long id = savedLaptop.getId();
        StringBuilder response = new StringBuilder("The id of saved laptop is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pc")
    public ResponseEntity<?> addPc(@RequestBody PcFeaturesDTO pcFeaturesDTO) {
        PcEntity savedPc = addProductService.savePc(pcFeaturesDTO);
        if (savedPc == null) {
            throw new SavingRecordException();
        }
        long id = savedPc.getId();
        StringBuilder response = new StringBuilder("The id of saved pc is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ExceptionHandler({SavingRecordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(SavingRecordException exception) {
        String savingRecordExceptionMessage = "Something went wrong during the saving the new record. Check input parameters\n";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage + exception.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception) {
        String savingRecordExceptionMessage = "Looks like you have chosen wrong parameter.\nCheck form_factor if it is PC, memory_volume if HDD, size if it is laptop";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage);
    }
}
