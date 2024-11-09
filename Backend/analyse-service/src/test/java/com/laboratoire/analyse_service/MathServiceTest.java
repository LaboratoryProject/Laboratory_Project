package com.laboratoire.analyse_service;

import com.laboratoire.analyse_service.service.MathService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestcontainersConfiguration.class)  // Import your Testcontainers configuration here
public class MathServiceTest {

    private final MathService mathService = new MathService();

    // PostgreSQL container will be injected by Spring
    @Autowired
    private static PostgreSQLContainer<?> postgresContainer;


    @BeforeAll
    public static void setUp() {
        // Start the container before tests run
        // You can also log the connection details to check if it's correct
        System.out.println("PostgreSQL container started: " + postgresContainer.getJdbcUrl());
    }

    @Test
    public void testAdd() {
        assertEquals(5, mathService.add(2, 3));
        assertEquals(0, mathService.add(-1, 1));
        assertEquals(-5, mathService.add(-2, -3));
    }

    @Test
    public void testPostgresConnection() {
        // You can add more tests here that interact with the Postgres container
        // This test will ensure that the PostgreSQL container is running
        assertTrue(postgresContainer.isRunning(), "PostgreSQL container should be running");
    }
}
