package ru.cft.shiftlab.gorin.testtask.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.ProductTypeNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.RecordNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.TooManyRecordsFoundException;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.ProductEntity;
import ru.cft.shiftlab.gorin.testtask.market.service.SearchProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/market/search")
public class SearchProductController {

    private final SearchProductService searchProductService;

    @Autowired
    public SearchProductController(SearchProductService searchProductService) {
        this.searchProductService = searchProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id)
            throws ProductTypeNotFoundException, RecordNotFoundException, TooManyRecordsFoundException {
        List<ProductEntity> products = searchProductService.findById(id);
        long numOfFoundProducts = products.size();
        if (numOfFoundProducts == 1) {
            ProductEntity product = products.get(0);
            switch (product.getProductType()) {
                case PC:
                    try {
                        return new ResponseEntity<>(searchProductService.findPcById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        throw new NoSuchElementException();
                    }
                case HDD:
                    try {
                        return new ResponseEntity<>(searchProductService.findHddById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        throw new NoSuchElementException();
                    }
                case LAPTOP:
                    try {
                        return new ResponseEntity<>(searchProductService.findLaptopById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        throw new NoSuchElementException();
                    }
                case MONITOR:
                    try {
                        return new ResponseEntity<>(searchProductService.findMonitorById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        throw new NoSuchElementException();
                    }
                default:
                    throw new ProductTypeNotFoundException();
            }
        }
        else if (numOfFoundProducts == 0) {
            throw new RecordNotFoundException();
        }
        else {
            throw new TooManyRecordsFoundException();
        }
    }


    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(NoSuchElementException exception) {
        String savingRecordExceptionMessage = "Something went wrong during the searching the new record.\n";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage + exception.getMessage());
    }

    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({TooManyRecordsFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(TooManyRecordsFoundException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
