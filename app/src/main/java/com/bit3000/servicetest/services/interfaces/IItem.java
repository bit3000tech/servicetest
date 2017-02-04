package com.bit3000.servicetest.services.interfaces;

import com.bit3000.servicetest.services.dto.ItemDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IItem {

    @GET("services/item")
    Call<List<ItemDto>> obtener();

    @POST("services/item")
    Call<ItemDto> guardar();

}
