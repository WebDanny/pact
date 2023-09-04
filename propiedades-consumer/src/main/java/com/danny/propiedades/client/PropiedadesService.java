package com.danny.propiedades.client;

import com.danny.propiedades.model.Propiedades;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface PropiedadesService {
    @GET("propiedades")
    Call<List<Propiedades>> getPropiedades();
}
