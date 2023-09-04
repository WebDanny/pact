package com.danny.propiedades;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.danny.propiedades.client.PropiedadesApiClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.*;

@ExtendWith(PactConsumerTestExt.class)
public class PropiedadApiClientPactTest {
    @Pact(provider = "propiedades_provider", consumer = "propiedades_provider")
    public V4Pact listOfPropiedadesPact(PactDslWithProvider builder) {
        return builder
                .given("there are propiedades")
                .uponReceiving("a request for propiedades")
                .path("/propiedades")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(newJsonArrayMinLike(1, a -> a.object(o -> {
                    o.id("id");
                    o.eachLike("items", i -> {
                        i.stringType("id");
                        i.stringType("nombre");
                        i.stringType("estado");
                        i.numberType("precio");
                    });
                })).build())
                .toPact(V4Pact.class);
    }

    @Pact(provider = "propiedades_provider", consumer = "propiedades_provider")
    public V4Pact noPropiedadesPact(PactDslWithProvider builder) {
        return builder
                .given("there are no propiedades")
                .uponReceiving("a request for propiedades")
                .path("/propiedades")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body("[]")
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "listOfPropiedadesPact")
    void getListOfPropiedades(MockServer mockServer) throws IOException {
        var client = new PropiedadesApiClient(mockServer.getUrl());
        var propiedades = client.fetchPropiedades();
        Assertions.assertNotNull(propiedades);
        Assertions.assertFalse(propiedades.isEmpty());
        Assertions.assertNotNull(propiedades.get(0).getItems());
        Assertions.assertFalse(propiedades.get(0).getItems().isEmpty());
    }

    @Test
    @PactTestFor(pactMethod = "noPropiedadesPact")
    void getEmptyListOfPropiedades(MockServer mockServer) throws IOException {
        var client = new PropiedadesApiClient(mockServer.getUrl());
        var propiedades = client.fetchPropiedades();
        Assertions.assertNotNull(propiedades);
        Assertions.assertTrue(propiedades.isEmpty());
    }
}
