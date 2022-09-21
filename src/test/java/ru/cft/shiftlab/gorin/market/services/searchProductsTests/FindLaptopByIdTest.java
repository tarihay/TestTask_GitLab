package ru.cft.shiftlab.gorin.market.services.searchProductsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.shiftlab.gorin.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.market.repositories.LaptopRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.market.services.impl.SearchProductServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static ru.cft.shiftlab.gorin.market.model.enums.ProductType.LAPTOP;

@ExtendWith(MockitoExtension.class)
public class FindLaptopByIdTest {

    @Mock
    private LaptopRepository laptopRepository;

    private LaptopEntity laptop;
    private LaptopEntity fakeLaptop;

    @Autowired
    @InjectMocks
    private SearchProductServiceImpl searchProductService;

    @BeforeEach
    public void setup(){
        laptop = new LaptopEntity("5555", "Mag", 1111111,1, LAPTOP, LaptopSize.THIRTEEN);
        fakeLaptop = new LaptopEntity("fake5555", "fakeMag", 10000,0, LAPTOP, LaptopSize.FOURTEEN);
    }
    @AfterEach
    void tearDown() {
        laptop = null;
    }

    /**
     * Positive case test
     */
    @Test
    public void searchingExistingLaptopTest() {
        when(laptopRepository.findById(laptop.getId())).thenReturn(Optional.ofNullable(laptop));
        assertThat(searchProductService.findLaptopById(laptop.getId()).get()).isEqualTo(laptop);
    }

    /**
     * Negative case test
     */
    @Test
    public void searchingNotExistingLaptopTest() {
        when(laptopRepository.findById(laptop.getId())).thenReturn(Optional.ofNullable(laptop));
        assertThat(searchProductService.findLaptopById(laptop.getId()).get()).isNotEqualTo(fakeLaptop);
    }
}
