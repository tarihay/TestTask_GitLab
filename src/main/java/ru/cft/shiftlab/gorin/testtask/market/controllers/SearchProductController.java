package ru.cft.shiftlab.gorin.testtask.market.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.ProductTypeNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.RecordNotFoundException;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.TooManyRecordsFoundException;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.*;
import ru.cft.shiftlab.gorin.testtask.market.services.SearchProductService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс-RectController, отвечающий за запросы пользователя по поиску товаров
 */
@RestController
@RequestMapping("/market/search")
public class SearchProductController {
    private static final Logger logger = LogManager.getLogger(SearchProductController.class);

    private final SearchProductService searchProductService;

    @Autowired
    public SearchProductController(SearchProductService searchProductService) {
        this.searchProductService = searchProductService;
    }

    /**
     * Метод, обабатывающий Get-запрос, который запрашивает товар с конкретным идентификатором
     * @param id Идентификатор нужного товара
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с телом нашедшегося товара
     * @throws ProductTypeNotFoundException в случае, если был указан несуществующий тип товара
     * @throws RecordNotFoundException в случае, если товар с этим id не был найден
     * @throws TooManyRecordsFoundException в случае, если товаров с этим айди было найдено две или более штуки
     * @throws NoSuchElementException в случае, если был найден элемент, но при парсинге Optional, произошла ошибка
     * @see java.util.Optional
     * @see SearchProductService
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id)
            throws ProductTypeNotFoundException, RecordNotFoundException, TooManyRecordsFoundException {
        List<ProductEntity> products = searchProductService.findById(id);
        if (products == null) {
            logger.warn("There is no record with such id " + id);
            throw new RecordNotFoundException();
        }
        long numOfFoundRecords = products.size();
        logger.info("The number of found records with id " + id + " is: " + numOfFoundRecords);
        if (numOfFoundRecords == 1) {
            ProductEntity product = products.get(0);
            logger.info("Found product: \n" + product);
            switch (product.getProductType()) {
                case PC:
                    try {
                        return new ResponseEntity<>(searchProductService.findPcById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        logger.error("Check returned Optional<Pc>");
                        throw new NoSuchElementException();
                    }
                case HDD:
                    try {
                        return new ResponseEntity<>(searchProductService.findHddById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        logger.error("Check returned Optional<Hdd>");
                        throw new NoSuchElementException();
                    }
                case LAPTOP:
                    try {
                        return new ResponseEntity<>(searchProductService.findLaptopById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        logger.error("Check returned Optional<Laptop>");
                        throw new NoSuchElementException();
                    }
                case MONITOR:
                    try {
                        return new ResponseEntity<>(searchProductService.findMonitorById(id).get(), HttpStatus.OK);
                    }
                    catch (NoSuchElementException ex) {
                        logger.error("Check returned Optional<Monitor>");
                        throw new NoSuchElementException();
                    }
                default:
                    logger.warn("No such product type found. Check Input");
                    throw new ProductTypeNotFoundException();
            }
        }
        else {
            logger.fatal("There are " + numOfFoundRecords + " records with such id. The number should be 1 or 0");
            throw new TooManyRecordsFoundException();
        }
    }

    /**
     * Метод, обабатывающий Get-запрос, который запрашивает все мониторы
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с телом, содержащим список найденных мониторов
     * @throws RecordNotFoundException в случае, если мониторов не было найдено
     * @see SearchProductService
     */
    @GetMapping("/monitors")
    public ResponseEntity<?> findAllMonitors() throws RecordNotFoundException {
        List<MonitorEntity> monitors = searchProductService.findAllMonitors();
        if (monitors == null) {
            logger.warn("No monitors found");
            throw new RecordNotFoundException();
        }
        logger.info("Found monitors: \n" + monitors);
        return new ResponseEntity<>(monitors, HttpStatus.OK);
    }
    /**
     * Метод, обабатывающий Get-запрос, который запрашивает все жесткие диски
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с телом, содержащим список найденных жестких дисков
     * @throws RecordNotFoundException в случае, если жестких дисков не было найдено
     * @see SearchProductService
     */
    @GetMapping("/hdds")
    public ResponseEntity<?> findAllHdds() throws RecordNotFoundException {
        List<HddEntity> hdds = searchProductService.findAllHdds();
        if (hdds == null) {
            logger.warn("No hdds found");
            throw new RecordNotFoundException();
        }
        logger.info("Found hdds: \n" + hdds);
        return new ResponseEntity<>(hdds, HttpStatus.OK);
    }

    /**
     * Метод, обабатывающий Get-запрос, который запрашивает все ноутбуки
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с телом, содержащим список найденных ноутбуков
     * @throws RecordNotFoundException в случае, если ноутбуков не было найдено
     * @see SearchProductService
     */
    @GetMapping("/laptops")
    public ResponseEntity<?> findAllLaptops() throws RecordNotFoundException {
        List<LaptopEntity> laptops = searchProductService.findAllLaptops();
        if (laptops == null) {
            logger.warn("No laptops found");
            throw new RecordNotFoundException();
        }
        logger.info("Found laptops: \n" + laptops);
        return new ResponseEntity<>(laptops, HttpStatus.OK);
    }

    /**
     * Метод, обабатывающий Get-запрос, который запрашивает все ПК
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с телом, содержащим список найденных ПК
     * @throws RecordNotFoundException в случае, если ни одного ПК не было найдено
     * @see SearchProductService
     */
    @GetMapping("/pcs")
    public ResponseEntity<?> findAllPcs() throws RecordNotFoundException {
        List<PcEntity> pcs = searchProductService.findAllPcs();
        if (pcs == null) {
            logger.warn("No pcs found");
            throw new RecordNotFoundException();
        }
        logger.info("Found pcs: \n" + pcs);
        return new ResponseEntity<>(pcs, HttpStatus.OK);
    }


    /**
     * ExceptionHandler, который обрабатывает исключение в случае ошибки в парсинге Optional
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая HTTP статус BAD REQUEST с кратким описанием ошибки
     * @see NoSuchElementException
     */
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(NoSuchElementException exception) {
        String savingRecordExceptionMessage = "Something went wrong during the searching the new record.\n";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage);
    }

    /**
     * ExceptionHandler, который обрабатывает исключение в случае ненахождения записей
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая HTTP статус NOT FOUND с кратким описанием
     * @see RecordNotFoundException
     */
    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        String recordNotFoundMessage = "Records were not found.\n";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recordNotFoundMessage);
    }

    /**
     * ExceptionHandler, который обрабатывает исключение в случае нахождения двух или более записей в месте, где это не предполагается
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая HTTP статус INTERNAL SERVER ERROR с кратким описанием
     * @see TooManyRecordsFoundException
     */
    @ExceptionHandler({TooManyRecordsFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(TooManyRecordsFoundException exception) {
        String tooManyRecordsFoundMessage = "Accidentally found several records\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tooManyRecordsFoundMessage);
    }

}
