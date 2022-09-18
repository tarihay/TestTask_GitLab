package ru.cft.shiftlab.gorin.testtask.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.RecordNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.TooManyRecordsFoundException;
import ru.cft.shiftlab.gorin.testtask.market.services.DeleteProductService;
import ru.cft.shiftlab.gorin.testtask.market.services.SearchProductService;

@RestController
@RequestMapping("/market/delete")
public class DeleteProductController {
    private static final String DELETED_SUCCESSFUL = "Deleted successfully";

    private final DeleteProductService deleteProductService;
    private final SearchProductService searchProductService;

    @Autowired
    public DeleteProductController(DeleteProductService deleteProductService, SearchProductService searchProductService) {
        this.deleteProductService = deleteProductService;
        this.searchProductService = searchProductService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable long id) throws RecordNotFoundException, TooManyRecordsFoundException {
        long numOfFoundRecords = searchProductService.findById(id).size();
        if (numOfFoundRecords == 1) {
            deleteProductService.deleteProductById(id);
            return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
        }
        else if (numOfFoundRecords == 0) {
            throw new RecordNotFoundException();
        }
        else {
            throw new TooManyRecordsFoundException();
        }
    }

    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        String recordNotFoundMessage = "Record with such id was not found.\n";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recordNotFoundMessage);
    }

    @ExceptionHandler({TooManyRecordsFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(TooManyRecordsFoundException exception) {
        String tooManyRecordsFoundMessage = "Accidentally found several records\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tooManyRecordsFoundMessage);
    }

}
