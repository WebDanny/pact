package com.danny.propiedades.client;

import com.danny.propiedades.model.Propiedades;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class PropiedadesApiClient {
    private final PropiedadesService propiedadesService;

    public PropiedadesApiClient(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        propiedadesService = retrofit.create(PropiedadesService.class);
    }

    public List<Propiedades> fetchPropiedades() throws IOException {
        Response<List<Propiedades>> response = propiedadesService.getPropiedades().execute();
        return response.body();
    }
}
