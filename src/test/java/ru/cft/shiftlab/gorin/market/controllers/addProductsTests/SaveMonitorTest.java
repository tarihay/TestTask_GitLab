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
import ru.cft.shiftlab.gorin.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.market.services.AddProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.cft.shiftlab.gorin.market.controllers.StringToJsonParser.asJsonString;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.MONITOR;

@ExtendWith(MockitoExtension.class)
public class SaveMonitorTest {

    @Mock
    private AddProductService addProductService;
    private MonitorEntity newMonitor;


    @InjectMocks
    private AddProductController addProductController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        newMonitor = new MonitorEntity("5555", "Mag", 1111111,1, MONITOR,  18);
        mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }
    @AfterEach
    void tearDown() {
        newMonitor = null;
    }

    /**
     * Positive case test
     * @throws Exception
     */
    @Test
    public void savingMonitorTest() throws Exception {
        when(addProductService.saveMonitor(any())).thenReturn(newMonitor);
        mockMvc.perform(post("/market/add/monitor").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newMonitor))).
                andExpect(status().isOk());
        verify(addProductService,times(1)).saveMonitor(any());
    }

    /**
     * Negative case test
     * @throws Exception
     */
    @Test
    public void savingMonitorToHddDtoTest() throws Exception {
        mockMvc.perform(post("/market/add/hdd").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(newMonitor))).
                andExpect(status().isBadRequest());
    }
}
