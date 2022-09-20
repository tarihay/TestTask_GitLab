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
import ru.cft.shiftlab.gorin.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.market.services.AddProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.cft.shiftlab.gorin.market.controllers.StringToJsonParser.asJsonString;
import static ru.cft.shiftlab.gorin.market.model.enums.MemoryVolumeOptions.GB;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.HDD;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.LAPTOP;

@ExtendWith(MockitoExtension.class)
public class SaveLaptopTest {
    @Mock
    private AddProductService addProductService;
    private LaptopEntity newLaptop;


    @InjectMocks
    private AddProductController addProductController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        newLaptop = new LaptopEntity("5555", "Mag", 1111111,1, LAPTOP, LaptopSize.THIRTEEN);
        mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }
    @AfterEach
    void tearDown() {
        newLaptop = null;
    }

    /**
     * Positive case test
     * @throws Exception
     */
    @Test
    public void savingLaptopTest() throws Exception {
        when(addProductService.saveLaptop(any())).thenReturn(newLaptop);
        mockMvc.perform(post("/market/add/laptop").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newLaptop))).
                andExpect(status().isOk());
        verify(addProductService,times(1)).saveLaptop(any());
    }

    /**
     * Negative case test
     * @throws Exception
     */
    @Test
    public void savingLaptopToPcDtoTest() throws Exception {
        mockMvc.perform(post("/market/add/pc").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newLaptop))).
                andExpect(status().isBadRequest());
    }

}
