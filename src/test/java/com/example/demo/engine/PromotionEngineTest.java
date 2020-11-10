package com.example.demo.engine;

import com.example.demo.data.Promotion;
import com.example.demo.data.SKU;
import com.example.demo.models.CheckOutItem;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.repository.SKURepository;
import com.example.demo.utils.PromotionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = { PromotionEngine.class })
public class PromotionEngineTest {

    @Autowired
    PromotionEngine promotionEngine;

    @MockBean
    PromotionRepository promotionRepository;

    @MockBean
    SKURepository skuRepository;

    @BeforeEach
    public void SetUp(){
        Mockito.when(skuRepository.findAll()).thenReturn(getSkuList());
        Mockito.when(promotionRepository.findAll()).thenReturn(getActivePromotion());
    }

    @Test
    public void testFinalPrice(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(5);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);

        // 3A + 2A = 130 + 2*50, 2B + 2B + 1B = 45 + 45 + 30 => 350
        assertEquals(Integer.valueOf(350), promotionEngine.applyPromotion(checkOutItems));
    }

    @Test
    public void testScenarioA(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(1);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(1);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);

        assertEquals(Integer.valueOf(100), promotionEngine.applyPromotion(checkOutItems));
    }

    @Test
    public void testScenarioB(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(5);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);

        assertEquals(Integer.valueOf(370), promotionEngine.applyPromotion(checkOutItems));
    }


    @Test
    public void testScenarioC(){

        List<CheckOutItem> checkOutItems = new ArrayList<>();
        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(3);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        CheckOutItem checkOutItemD = new CheckOutItem();
        checkOutItemD.setSku("D");
        checkOutItemD.setQuantity(1);

        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);
        checkOutItems.add(checkOutItemD);

        assertEquals(Integer.valueOf(280), promotionEngine.applyPromotion(checkOutItems));
    }

    private List<SKU>  getSkuList(){
        List<SKU> skus = new ArrayList<>();

        SKU sku = new SKU();
        sku.setSku("A");
        sku.setSkuPrice(50);

        SKU sku1 = new SKU();
        sku1.setSku("B");
        sku1.setSkuPrice(30);

        SKU sku2 = new SKU();
        sku2.setSku("C");
        sku2.setSkuPrice(20);

        SKU sku3 = new SKU();
        sku3.setSku("D");
        sku3.setSkuPrice(15);

        skus.add(sku);
        skus.add(sku1);
        skus.add(sku2);
        skus.add(sku3);

        return skus;
    }

    public List<Promotion> getActivePromotion(){
        List<Promotion> activePromotions = new ArrayList<>();
        Promotion promotion = new Promotion();
        promotion.setId(1);
        promotion.setType(PromotionType.QUANTITY.getPromotionType());
        promotion.setSkus("A");
        promotion.setQuantity(3);
        promotion.setDiscountedPrice(130);

        Promotion promotion2 = new Promotion();
        promotion2.setId(2);
        promotion2.setType(PromotionType.QUANTITY.getPromotionType());
        promotion2.setSkus("B");
        promotion2.setQuantity(2);
        promotion2.setDiscountedPrice(45);

        Promotion promotion3 = new Promotion();
        promotion3.setId(3);
        promotion3.setType(PromotionType.COMBINATION.getPromotionType());
        promotion3.setSkus("C,D");
        promotion3.setQuantity(1);
        promotion3.setDiscountedPrice(30);

        activePromotions.add(promotion);
        activePromotions.add(promotion2);
        activePromotions.add(promotion3);
        return activePromotions;
    }

}
