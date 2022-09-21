package ru.cft.shiftlab.gorin.market.services.searchProductsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.shiftlab.gorin.market.repositories.MonitorRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.market.services.impl.SearchProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.MONITOR;

@ExtendWith(MockitoExtension.class)
public class FindMonitorByIdTest {

    @Mock
    private MonitorRepository monitorRepository;

    private MonitorEntity monitor;
    private MonitorEntity fakeMonitor;

    @Autowired
    @InjectMocks
    private SearchProductServiceImpl searchProductService;

    @BeforeEach
    public void setup(){
        monitor = new MonitorEntity("5555", "Mag", 1111111,1, MONITOR,  15);
        fakeMonitor = new MonitorEntity("fake5555", "fakeMag", 10000,0, MONITOR,  10);
    }
    @AfterEach
    void tearDown() {
        monitor = null;
    }

    /**
     * Positive case test
     */
    @Test
    public void searchingExistingMonitorTest() {
        when(monitorRepository.findById(monitor.getId())).thenReturn(Optional.ofNullable(monitor));
        assertThat(searchProductService.findMonitorById(monitor.getId()).get()).isEqualTo(monitor);
    }

    /**
     * Negative case test
     */
    @Test
    public void searchingNotExistingMonitorTest() {
        when(monitorRepository.findById(monitor.getId())).thenReturn(Optional.ofNullable(monitor));
        assertThat(searchProductService.findMonitorById(monitor.getId()).get()).isNotEqualTo(fakeMonitor);
    }
}
