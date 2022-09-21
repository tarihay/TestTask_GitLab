package ru.cft.shiftlab.gorin.market.services.searchProductsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.shiftlab.gorin.market.model.enums.PcFormFactors;
import ru.cft.shiftlab.gorin.market.repositories.PcRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.market.services.impl.SearchProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.PC;

@ExtendWith(MockitoExtension.class)
public class FindPcByIdTest {

    @Mock
    private PcRepository pcRepository;

    private PcEntity pc;
    private PcEntity fakePc;

    @Autowired
    @InjectMocks
    private SearchProductServiceImpl searchProductService;

    @BeforeEach
    public void setup(){
        pc = new PcEntity("5555", "Mag", 1111111,1, PC, PcFormFactors.NETTOP);
        fakePc = new PcEntity("fake5555", "fakeMag", 10000,0, PC, PcFormFactors.DESKTOP);
    }
    @AfterEach
    void tearDown() {
        pc = null;
    }

    /**
     * Positive case test
     */
    @Test
    public void searchingExistingPcTest() {
        when(pcRepository.findById(pc.getId())).thenReturn(Optional.ofNullable(pc));
        assertThat(searchProductService.findPcById(pc.getId()).get()).isEqualTo(pc);
    }

    /**
     * Negative case test
     */
    @Test
    public void searchingNotExistingPcTest() {
        when(pcRepository.findById(pc.getId())).thenReturn(Optional.ofNullable(pc));
        assertThat(searchProductService.findPcById(pc.getId()).get()).isNotEqualTo(fakePc);
    }
}
