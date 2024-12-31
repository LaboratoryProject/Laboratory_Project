package com.laboratoire.patient_service.controller;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;  // Assurez-vous d'avoir le client secret

    @Value("${keycloak-admin.username}")
    private String username;

    @Value("${keycloak-admin.password}")
    private String password;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)  // Ajoutez le client secret si nécessaire
                .username(username)
                .password(password)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }


    @Bean
    public ClientsResource clientsResource(Keycloak keycloak) {
        return keycloak.realm(realm).clients();
    }

    @Bean
    public String clientUUID(Keycloak keycloak) {
        return clientsResource(keycloak).findByClientId(clientId)
                .stream()
                .findFirst()
                .map(ClientRepresentation::getId)
                .orElseThrow(() -> new RuntimeException("Client not found in Keycloak"));
    }

    @Bean
    public UsersResource usersResource(Keycloak keycloak) {
        return keycloak.realm(realm).users();
    }

    @Bean
    public RolesResource rolesResource(Keycloak keycloak) {
        return keycloak.realm(realm).roles();
    }

    @Bean
    public GroupsResource groupsResource(Keycloak keycloak) {
        return keycloak.realm(realm).groups();
    }


}
