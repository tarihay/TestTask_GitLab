package ru.cft.shiftlab.gorin.market.controllers.addProductsTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.cft.shiftlab.gorin.market.controllers.AddProductController;
import ru.cft.shiftlab.gorin.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.market.services.AddProductService;
import ru.cft.shiftlab.gorin.market.model.enums.PcFormFactors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.cft.shiftlab.gorin.market.controllers.StringToJsonParser.asJsonString;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.PC;

@ExtendWith(MockitoExtension.class)
public class SavePcTest {

    @Mock
    private AddProductService addProductService;
    private PcEntity newPc;


    @InjectMocks
    private AddProductController addProductController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        newPc = new PcEntity("5555", "Mag", 1111111,1, PC,  PcFormFactors.NETTOP);
        mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }
    @AfterEach
    void tearDown() {
        newPc = null;
    }

    /**
     * Positive case test
     * @throws Exception
     */
    @Test
    public void savingPcTest() throws Exception {
        when(addProductService.savePc(any())).thenReturn(newPc);
        mockMvc.perform(post("/market/add/pc").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newPc))).
                        andExpect(status().isOk());
        verify(addProductService,times(1)).savePc(any());
    }

    /**
     * Negative case test
     * @throws Exception
     */
    @Test
    public void savingPcToHddDtoTest() throws Exception {
        mockMvc.perform(post("/market/add/hdd").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newPc))).
                andExpect(status().isBadRequest());
    }
}
