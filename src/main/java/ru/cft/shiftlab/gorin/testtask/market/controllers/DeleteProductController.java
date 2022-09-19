package ru.cft.shiftlab.gorin.testtask.market.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.RecordNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.TooManyRecordsFoundException;
import ru.cft.shiftlab.gorin.testtask.market.services.DeleteProductService;
import ru.cft.shiftlab.gorin.testtask.market.services.SearchProductService;

/**
 * Класс-RectController, отвечающий за запросы пользователя по удалению товара
 */
@RestController
@RequestMapping("/market/delete")
public class DeleteProductController {
    private static final Logger logger = LogManager.getLogger(DeleteProductController.class);


    private static final String DELETED_SUCCESSFUL = "Deleted successfully";

    private final DeleteProductService deleteProductService;
    private final SearchProductService searchProductService;

    @Autowired
    public DeleteProductController(DeleteProductService deleteProductService, SearchProductService searchProductService) {
        this.deleteProductService = deleteProductService;
        this.searchProductService = searchProductService;
    }

    //TODO добавить документацию к оставшимся классам и добавить логирование
    /**
     * Метод, обабатывающий Post-запрос, который требует удаления товара по идентификатору, указанному в endpoint'e
     * @param id указанный идентификатор
     * @return Возвращается сущность ответа, содержащая HTTP статус OK
     * @throws RecordNotFoundException в случае отсутсвтвия товара с этим идентификатором
     * @throws TooManyRecordsFoundException в случае нахождения двух или более товаров с этим идентификатором
     * @see DeleteProductService
     * @see SearchProductService
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable long id) throws RecordNotFoundException, TooManyRecordsFoundException {
        long numOfFoundRecords = searchProductService.findById(id).size();
        logger.info("The number of found records with id " + id + " is: " + numOfFoundRecords);
        if (numOfFoundRecords == 1) {
            deleteProductService.deleteProductById(id);
            logger.info("Deleting passed successfully");
            return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
        }
        else if (numOfFoundRecords == 0) {
            logger.warn("There is no record with such id " + id);
            throw new RecordNotFoundException();
        }
        else {
            logger.fatal("There are " + numOfFoundRecords + " records with such id. The number should be 1 or 0");
            throw new TooManyRecordsFoundException();
        }
    }

    /**
     * ExceptionHandler, который обрабатывает исключение вызванное отсутсвием товара
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая статус NOT_FOUND и краткое описание проблемы
     * @see RecordNotFoundException
     */
    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        String recordNotFoundMessage = "Record with such id was not found.\n";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recordNotFoundMessage);
    }

    /**
     * ExceptionHandler, который обрабатывает исключение вызванное нахождением списка товаров, хотя данное не предполагалось
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая статус INTERNAL_SERVER_ERROR (так как произошла ошибка еще при записи товара)
     * и краткое описание проблемы
     * @see TooManyRecordsFoundException
     */
    @ExceptionHandler({TooManyRecordsFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(TooManyRecordsFoundException exception) {
        String tooManyRecordsFoundMessage = "Accidentally found several records\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tooManyRecordsFoundMessage);
    }
}
