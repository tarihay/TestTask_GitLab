package ru.cft.shiftlab.gorin.testtask.market.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import ru.cft.shiftlab.gorin.testtask.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.PcFormFactors;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.services.AddProductService;

/**
 * Класс-RectController, отвечающий за запросы пользователя по добавлению товара
 */
@RestController
@RequestMapping("/market/add")
public class AddProductController {
    private static final Logger logger = LogManager.getLogger(AddProductController.class);

    private final AddProductService addProductService;

    @Autowired
    public AddProductController(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    /**
     * Метод, обабатывающий Post-запрос, который отправляет MonitorDTO. Метод передает его сервису по добавлению товаров
     * @param monitorFeaturesDTO на вход поступает DTO монитора, со всеми параметрами его сущности
     * @return Возвращается сущность ответа, содержащая HTTP статус OK
     * @throws SavingRecordException в случае ошибки добавления товара
     * @see MonitorFeaturesDTO
     * @see AddProductService
     */
    @PostMapping("/monitor")
    public ResponseEntity<?> addMonitor(@RequestBody MonitorFeaturesDTO monitorFeaturesDTO) {
        MonitorEntity savedMonitor = addProductService.saveMonitor(monitorFeaturesDTO);
        if (savedMonitor == null) {
            logger.error("Saving monitor passed unsuccessfully");
            throw new SavingRecordException();
        }
        long id = savedMonitor.getId();
        logger.info("The id of saved monitor is: " + id);
        StringBuilder response = new StringBuilder("The id of saved monitor is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Метод, обабатывающий Post-запрос, который отправляет HddDTO. Метод передает его сервису по добавлению товаров
     * @param hddFeaturesDTO на вход поступает DTO жесткого диска, со всеми параметрами его сущности
     * @return Возвращается сущность ответа, содержащая HTTP статус OK
     * @throws SavingRecordException в случае ошибки добавления товара
     * @see HddFeaturesDTO
     * @see AddProductService
     */
    @PostMapping("/hdd")
    public ResponseEntity<?> addHdd(@RequestBody HddFeaturesDTO hddFeaturesDTO) {
        HddEntity savedHdd = addProductService.saveHdd(hddFeaturesDTO);
        if (savedHdd == null) {
            logger.error("Saving hdd passed unsuccessfully");
            throw new SavingRecordException();
        }
        long id = savedHdd.getId();
        logger.info("The id of saved hdd is: " + id);
        StringBuilder response = new StringBuilder("The id of saved hdd is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Метод, обабатывающий Post-запрос, который отправляет LaptopDTO. Метод передает его сервису по добавлению товаров
     * @param laptopFeaturesDTO на вход поступает DTO ноутбука, со всеми параметрами его сущности
     * @return Возвращается сущность ответа, содержащая HTTP статус OK
     * @throws SavingRecordException в случае ошибки добавления товара
     * @see LaptopFeaturesDTO
     * @see AddProductService
     */
    @PostMapping("/laptop")
    public ResponseEntity<?> addLaptop(@RequestBody LaptopFeaturesDTO laptopFeaturesDTO) {
        LaptopEntity savedLaptop = addProductService.saveLaptop(laptopFeaturesDTO);
        if (savedLaptop == null) {
            logger.error("Saving laptop passed unsuccessfully");
            throw new SavingRecordException();
        }
        long id = savedLaptop.getId();
        logger.info("The id of saved laptop is: " + id);
        StringBuilder response = new StringBuilder("The id of saved laptop is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Метод, обабатывающий Post-запрос, который отправляет PcDTO. Метод передает его сервису по добавлению товаров
     * @param pcFeaturesDTO на вход поступает DTO ПК, со всеми параметрами его сущности
     * @return Возвращается сущность ответа, содержащая HTTP статус OK
     * @throws SavingRecordException в случае ошибки добавления товара
     * @see PcFeaturesDTO
     * @see AddProductService
     */
    @PostMapping("/pc")
    public ResponseEntity<?> addPc(@RequestBody PcFeaturesDTO pcFeaturesDTO) {
        PcEntity savedPc = addProductService.savePc(pcFeaturesDTO);
        if (savedPc == null) {
            logger.error("Saving PC passed unsuccessfully");
            throw new SavingRecordException();
        }
        long id = savedPc.getId();
        logger.info("The id of saved PC is: " + id);
        StringBuilder response = new StringBuilder("The id of saved pc is: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * ExceptionHandler, который обрабатывает исключение вызванное ошибкой сохранения записи
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая статус BAD_REQUEST и краткое описание проблемы
     * @see SavingRecordException
     */
    @ExceptionHandler({SavingRecordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(SavingRecordException exception) {
        String savingRecordExceptionMessage = "Something went wrong during the saving the new record. Check input parameters\n";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage + exception.getMessage());
    }

    /**
     * ExceptionHandler, который обрабатывает исключение вызванное ошибкой парсинга пришедшей DTO
     * Исключение может быть вызвано в случаях:
     *      1) На вход PcDTO в аттрибуте "formFactor" пришло значение не принадлежащее enum-классу PcFormFactors;
     *      2) На вход HddDTO в аттрибуте "memoryVolume" пришло значение не принадлежащее enum-классу MemoryVolumeOptions;
     *      3) На вход LaptopDTO в аттрибуте "size" пришло значение не принадлежащее enum-классу LaptopSize;
     * @param exception Исключение по сохранению записи
     * @return Возвращается сущность ответа, содержащая статус BAD_REQUEST и краткое описание проблемы
     * @see PcFeaturesDTO
     * @see HddFeaturesDTO
     * @see LaptopFeaturesDTO
     * @see PcFormFactors
     * @see MemoryVolumeOptions
     * @see LaptopSize
     * @see HttpMessageNotReadableException
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception) {
        String savingRecordExceptionMessage = "Looks like you have chosen wrong parameter.\nCheck form_factor if it is PC, memory_volume if HDD, size if it is laptop. Or it could be another parsing error";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage);
    }
}
