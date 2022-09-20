package ru.cft.shiftlab.gorin.market.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.market.exceptions.*;
import ru.cft.shiftlab.gorin.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.market.model.enums.MemoryVolumeOptions;
import ru.cft.shiftlab.gorin.market.repositories.model.*;
import ru.cft.shiftlab.gorin.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.enums.PcFormFactors;
import ru.cft.shiftlab.gorin.market.services.SearchProductService;
import ru.cft.shiftlab.gorin.market.services.UpdateService;

import java.util.List;

/**
 * Класс-RectController, отвечающий за запросы пользователя по частичному или полному обновлению товаров
 */
@RestController
@RequestMapping("/market/update")
public class UpdateProductController {
    private static final Logger logger = LogManager.getLogger(UpdateProductController.class);

    private static final String MONITOR_SUCCESSFULLY_UPDATED = "The current monitor successfully updated";
    private static final String LAPTOP_SUCCESSFULLY_UPDATED = "The current laptop successfully updated";
    private static final String HDD_SUCCESSFULLY_UPDATED = "The current hdd successfully updated";
    private static final String PC_SUCCESSFULLY_UPDATED = "The current pc successfully updated";

    private final UpdateService updateService;
    private final SearchProductService searchProductService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public UpdateProductController(UpdateService updateService, SearchProductService searchProductService) {
        this.updateService = updateService;
        this.searchProductService = searchProductService;
    }

    /**
     * Метод, обрабатывающий Patch-запрос, который отправляет Json с параметрами обновления и id товара, который нужно обновить
     * Обработанные данные передает в updateService
     * @param id идентификатор товара, который нужно обновить
     * @param patch специальный Json-объект, который содержит информацию по обновлению
     * @return Возвращается сущность ответа, содержащая HTTP статус OK с подтверждением обновления
     * @throws RecordNotFoundException в случае, если товар с этим id не был найден
     * @throws JsonParsingException в случае, если произошла ошибка парсинга пришедшего Json-объекта
     * @throws UpdatingRecordException в случае, если произошла ошибка обновления
     * @see UpdateService
     */
    @PatchMapping( "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody JsonPatch patch) throws RecordNotFoundException, JsonParsingException {
        try {
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
                        PcEntity pc = searchProductService.findPcById(id).get();
                        PcEntity pcPatched = applyPatchToPc(patch, pc);
                        logger.info("Parsing Json-Object with PcFeatures passed successfully");
                        PcEntity updatedPc = updateService.updatePc(pcPatched);
                        if (updatedPc == null) {
                            logger.error("Updating Pc passed unsuccessfully");
                            throw new UpdatingRecordException();
                        }
                        logger.info("Updating Pc passed successfully");
                        return new ResponseEntity<>(PC_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case HDD:
                        HddEntity hdd = searchProductService.findHddById(id).get();
                        HddEntity hddPatched = applyPatchToHdd(patch, hdd);
                        logger.info("Parsing Json-Object with HddFeatures passed successfully");
                        HddEntity updatedHdd = updateService.updateHdd(hddPatched);
                        if (updatedHdd == null) {
                            logger.error("Updating Hdd passed unsuccessfully");
                            throw new UpdatingRecordException();
                        }
                        logger.info("Updating Hdd passed successfully");
                        return new ResponseEntity<>(HDD_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case LAPTOP:
                        LaptopEntity laptop = searchProductService.findLaptopById(id).get();
                        LaptopEntity laptopPatched = applyPatchToLaptop(patch, laptop);
                        logger.info("Parsing Json-Object with LaptopFeatures passed successfully");
                        LaptopEntity updatedLaptop = updateService.updateLaptop(laptopPatched);
                        if (updatedLaptop == null) {
                            logger.error("Updating Laptop passed unsuccessfully");
                            throw new UpdatingRecordException();
                        }
                        logger.info("Updating Laptop passed successfully");
                        return new ResponseEntity<>(LAPTOP_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case MONITOR:
                        MonitorEntity monitor = searchProductService.findMonitorById(id).get();
                        MonitorEntity monitorPatched = applyPatchToMonitor(patch, monitor);
                        logger.info("Parsing Json-Object with MonitorFeatures passed successfully");
                        MonitorEntity updatedMonitor = updateService.updateMonitor(monitorPatched);
                        if (updatedMonitor == null) {
                            logger.error("Updating Monitor passed unsuccessfully");
                            throw new UpdatingRecordException();
                        }
                        logger.info("Updating Monitor passed successfully");
                        return new ResponseEntity<>(MONITOR_SUCCESSFULLY_UPDATED, HttpStatus.OK);
                    default:
                        logger.warn("No such product type found. Check Input");
                        throw new ProductTypeNotFoundException();
                }
            }
            else {
                logger.fatal("There are " + numOfFoundRecords + " records with such id. The number should be 1 or 0");
                throw new TooManyRecordsFoundException();
            }
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonParsingException(e.getMessage());
        } catch (RecordNotFoundException | ProductTypeNotFoundException e) {
            throw new RecordNotFoundException();
        } catch (TooManyRecordsFoundException | UpdatingRecordException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * ExceptionHandler, который обрабатывает исключение вызванное ошибкой обновления сущности с заданным id
     * Выбрасывается в случае перехвата TooManyRecordsFoundException, UpdatingRecordException
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая статус INTERNAL_SERVER_ERROR и краткое описание проблемы
     * @see TooManyRecordsFoundException
     * @see UpdatingRecordException
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(RuntimeException exception) {
        final String runtimeExceptionMessage = "Updating passed unsuccessfully\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runtimeExceptionMessage);
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
        final String runtimeExceptionMessage = "Something went wrong. Check input parameters\n";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeExceptionMessage);
    }

    /**
     * ExceptionHandler, который обрабатывает исключение вызванное ошибкой парсинга Json-объекта
     * @param exception перехватываемое исключение
     * @return Возвращается сущность ответа, содержащая статус INTERNAL_SERVER_ERROR и краткое описание проблемы
     * @see JsonParsingException
     */
    @ExceptionHandler({JsonParsingException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(JsonParsingException exception) {
        final String jsonParsingExceptionMessage = "Json parsing passed unsuccessfully";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonParsingExceptionMessage);
    }

    /**
     * ExceptionHandler, который обрабатывает исключение вызванное ошибкой парсинга пришедшей DTO
     * Исключение может быть вызвано в случаях:
     *      1) На вход PcDTO в аттрибуте "formFactor" пришло значение не принадлежащее enum-классу PcFormFactors;
     *      2) На вход HddDTO в аттрибуте "memoryVolume" пришло значение не принадлежащее enum-классу MemoryVolumeOptions;
     *      3) На вход LaptopDTO в аттрибуте "size" пришло значение не принадлежащее enum-классу LaptopSize;
     * @param exception перехватываемое исключение
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
        String savingRecordExceptionMessage = "Looks like you have chosen wrong parameter.\nCheck form_factor if it is PC, memory_volume if HDD, size if it is laptop";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage);
    }

    /**
     * Метод парсящий Json-объект в объект монитора
     * @param patch пришедший Json-объект
     * @param targetMonitor сущность монитора
     * @return Возвращается распарсенный монитор
     * @throws JsonPatchException
     * @throws JsonProcessingException
     * @see MonitorEntity
     */
    private MonitorEntity applyPatchToMonitor(
            JsonPatch patch, MonitorEntity targetMonitor) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMonitor, JsonNode.class));
        return objectMapper.treeToValue(patched, MonitorEntity.class);
    }

    /**
     * Метод парсящий Json-объект в объект ноутбука
     * @param patch пришедший Json-объект
     * @param targetLaptop сущность ноутбука
     * @return Возвращается распарсенный ноутбук
     * @throws JsonPatchException
     * @throws JsonProcessingException
     * @see LaptopEntity
     */
    private LaptopEntity applyPatchToLaptop(
            JsonPatch patch, LaptopEntity targetLaptop) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetLaptop, JsonNode.class));
        return objectMapper.treeToValue(patched, LaptopEntity.class);
    }

    /**
     * Метод парсящий Json-объект в объект ПК
     * @param patch пришедший Json-объект
     * @param targetPc сущность ПК
     * @return Возвращается распарсенный ПК
     * @throws JsonPatchException
     * @throws JsonProcessingException
     * @see PcEntity
     */
    private PcEntity applyPatchToPc(
            JsonPatch patch, PcEntity targetPc) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPc, JsonNode.class));
        return objectMapper.treeToValue(patched, PcEntity.class);
    }

    /**
     * Метод парсящий Json-объект в объект жесткого диска
     * @param patch пришедший Json-объект
     * @param targetHdd сущность жесткого диска
     * @return Возвращается распарсенный жесткого диска
     * @throws JsonPatchException
     * @throws JsonProcessingException
     * @see HddEntity
     */
    private HddEntity applyPatchToHdd(
            JsonPatch patch, HddEntity targetHdd) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetHdd, JsonNode.class));
        return objectMapper.treeToValue(patched, HddEntity.class);
    }
}
