package ru.cft.shiftlab.gorin.testtask.market.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.cft.shiftlab.gorin.testtask.market.exceptions.*;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.*;
import ru.cft.shiftlab.gorin.testtask.market.service.SearchProductService;
import ru.cft.shiftlab.gorin.testtask.market.service.UpdateService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/market/update")
public class UpdateProductController {
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

    @PatchMapping( "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody JsonPatch patch) throws RecordNotFoundException, JsonParsingException {
        try {
            List<ProductEntity> products = searchProductService.findById(id);
            if (products == null) {
                throw new RecordNotFoundException();
            }
            long numOfFoundProducts = products.size();
            if (numOfFoundProducts == 1) {
                ProductEntity product = products.get(0);
                switch (product.getProductType()) {
                    case PC:
                        PcEntity pc = searchProductService.findPcById(id).get();
                        PcEntity pcPatched = applyPatchToPc(patch, pc);
                        PcEntity updatedPc = updateService.updatePc(pcPatched);
                        if (updatedPc == null) {
                            throw new UpdatingRecordException();
                        }
                        return new ResponseEntity<>(PC_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case HDD:
                        HddEntity hdd = searchProductService.findHddById(id).get();
                        HddEntity hddPatched = applyPatchToHdd(patch, hdd);
                        HddEntity updatedHdd = updateService.updateHdd(hddPatched);
                        if (updatedHdd == null) {
                            throw new UpdatingRecordException();
                        }
                        return new ResponseEntity<>(HDD_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case LAPTOP:
                        LaptopEntity laptop = searchProductService.findLaptopById(id).get();
                        LaptopEntity laptopPatched = applyPatchToLaptop(patch, laptop);
                        LaptopEntity updatedLaptop = updateService.updateLaptop(laptopPatched);
                        if (updatedLaptop == null) {
                            throw new UpdatingRecordException();
                        }
                        return new ResponseEntity<>(LAPTOP_SUCCESSFULLY_UPDATED, HttpStatus.OK);

                    case MONITOR:
                        MonitorEntity monitor = searchProductService.findMonitorById(id).get();
                        MonitorEntity monitorPatched = applyPatchToMonitor(patch, monitor);
                        MonitorEntity updatedMonitor = updateService.updateMonitor(monitorPatched);
                        if (updatedMonitor == null) {
                            throw new UpdatingRecordException();
                        }
                        return new ResponseEntity<>(MONITOR_SUCCESSFULLY_UPDATED, HttpStatus.OK);
                    default:
                        throw new ProductTypeNotFoundException();
                }
            }
            else {
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


    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(RuntimeException exception) {
        final String runtimeExceptionMessage = "Updating passed unsuccessfully\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runtimeExceptionMessage);
    }

    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        final String runtimeExceptionMessage = "Something went wrong. Check input parameters\n";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeExceptionMessage);
    }

    @ExceptionHandler({JsonParsingException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(JsonParsingException exception) {
        final String jsonParsingExceptionMessage = "Json parsing passed unsuccessfully";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonParsingExceptionMessage);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception) {
        String savingRecordExceptionMessage = "Looks like you have chosen wrong parameter.\nCheck form_factor if it is PC, memory_volume if HDD, size if it is laptop";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savingRecordExceptionMessage);
    }

    private MonitorEntity applyPatchToMonitor(
            JsonPatch patch, MonitorEntity targetMonitor) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMonitor, JsonNode.class));
        return objectMapper.treeToValue(patched, MonitorEntity.class);
    }

    private LaptopEntity applyPatchToLaptop(
            JsonPatch patch, LaptopEntity targetLaptop) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetLaptop, JsonNode.class));
        return objectMapper.treeToValue(patched, LaptopEntity.class);
    }

    private PcEntity applyPatchToPc(
            JsonPatch patch, PcEntity targetPc) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPc, JsonNode.class));
        return objectMapper.treeToValue(patched, PcEntity.class);
    }

    private HddEntity applyPatchToHdd(
            JsonPatch patch, HddEntity targetHdd) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetHdd, JsonNode.class));
        return objectMapper.treeToValue(patched, HddEntity.class);
    }
}
