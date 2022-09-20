package ru.cft.shiftlab.gorin.market.services.searchProductsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.shiftlab.gorin.market.repositories.HddRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.market.services.impl.SearchProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static ru.cft.shiftlab.gorin.market.model.enums.MemoryVolumeOptions.GB;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.HDD;

@ExtendWith(MockitoExtension.class)
public class FindHddByIdTest {

    @Mock
    private HddRepository hddRepository;

    private HddEntity hdd;
    private HddEntity fakeHdd;

    @Autowired
    @InjectMocks
    private SearchProductServiceImpl searchProductService;

    @BeforeEach
    public void setup(){
        hdd = new HddEntity("5555", "Mag", 1111111,1, HDD,  123, GB);
        fakeHdd = new HddEntity("fake5555", "fakeMag", 10000,0, HDD,  123, GB);
    }
    @AfterEach
    void tearDown() {
        hdd = null;
    }

    /**
     * Positive case test
     */
    @Test
    public void searchingExistingHddTest() {
        when(hddRepository.findById(hdd.getId())).thenReturn(Optional.ofNullable(hdd));
        assertThat(searchProductService.findHddById(hdd.getId()).get()).isEqualTo(hdd);
    }

    /**
     * Negative case test
     */
    @Test
    public void searchingNotExistingHddTest() {
        when(hddRepository.findById(hdd.getId())).thenReturn(Optional.ofNullable(hdd));
        assertThat(searchProductService.findHddById(hdd.getId()).get()).isNotEqualTo(fakeHdd);
    }
}
