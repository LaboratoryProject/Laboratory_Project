package com.laboratoire.analyse_service;

import com.laboratoire.analyse_service.service.MathService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("local")
public class MathServiceTest {

    private final MathService mathService = new MathService();

    @Test
    public void testAdd() {
        assertEquals(5, mathService.add(2, 3));
        assertEquals(0, mathService.add(-1, 1));
        assertEquals(-5, mathService.add(-2, -3));
    }
}
