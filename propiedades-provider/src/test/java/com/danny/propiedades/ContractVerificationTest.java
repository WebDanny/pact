package com.danny.propiedades;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.danny.propiedades.model.Propiedades;
import com.danny.propiedades.repository.PropiedadsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("propiedades_provider")
@PactBroker
public class ContractVerificationTest {
    @MockBean
    private PropiedadsRepository propiedadesRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("there are propiedades")
    public void thereArePropiedades() throws IOException {
        Mockito.when(propiedadesRepository.getPropiedades()).thenReturn(getOrdersFromFile("propiedades.json"));
    }

    @State("there are no propiedades")
    public void thereAreNoPropiedades() throws IOException {
        Mockito.when(propiedadesRepository.getPropiedades()).thenReturn(getOrdersFromFile("no_propiedades.json"));
    }

    private List<Propiedades> getOrdersFromFile(String filename) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }
}
