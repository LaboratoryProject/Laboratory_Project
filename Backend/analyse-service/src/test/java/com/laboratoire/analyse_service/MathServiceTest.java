package com.laboratoire.analyse_service;

import com.laboratoire.analyse_service.controller.MathController;
import com.laboratoire.analyse_service.service.MathService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Initializes mocks
public class MathServiceTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    @Test
    public void testAdd() {
        // Arrange: mock behavior of mathService
        when(mathService.add(2, 3)).thenReturn(5);

        // Act: call the method in MathController
        int result = mathController.add(2, 3);

        // Assert: verify the result
        assertEquals(5, result);
    }
}
