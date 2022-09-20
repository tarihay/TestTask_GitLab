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
import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.market.services.AddProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.cft.shiftlab.gorin.market.controllers.StringToJsonParser.asJsonString;
import static ru.cft.shiftlab.gorin.market.model.enums.MemoryVolumeOptions.GB;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.HDD;

@ExtendWith(MockitoExtension.class)
public class SaveHddTest {
    @Mock
    private AddProductService addProductService;
    private HddEntity newHdd;


    @InjectMocks
    private AddProductController addProductController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        newHdd = new HddEntity("5555", "Mag", 1111111,1, HDD,  123, GB);
        mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }
    @AfterEach
    void tearDown() {
        newHdd = null;
    }

    /**
     * Positive case test
     * @throws Exception
     */
    @Test
    public void savingHddTest() throws Exception {
        when(addProductService.saveHdd(any())).thenReturn(newHdd);
        mockMvc.perform(post("/market/add/hdd").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newHdd))).
                andExpect(status().isOk());
        verify(addProductService,times(1)).saveHdd(any());
    }

    /**
     * Negative case test
     * @throws Exception
     */
    @Test
    public void savingHddToPcDtoTest() throws Exception {
        mockMvc.perform(post("/market/add/pc").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newHdd))).
                andExpect(status().isBadRequest());
    }
}
