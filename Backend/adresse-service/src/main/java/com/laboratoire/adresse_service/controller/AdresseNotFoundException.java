package com.laboratoire.adresse_service.controller;

public class AdresseNotFoundException extends RuntimeException {
    public AdresseNotFoundException(String message) {
        super(message);
    }
}