package com.bit3000.servicetest.services;

import com.bit3000.servicetest.services.base.ServiceGenerator;
import com.bit3000.servicetest.services.callbacks.IServiceAsyncManager;
import com.bit3000.servicetest.services.dto.ItemDto;
import com.bit3000.servicetest.services.dto.ServiceResponse;
import com.bit3000.servicetest.services.interfaces.IItem;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAgente extends ServiceGenerator{

    private IItem mClient;

    public ItemAgente(){
        super();
        mClient = retrofit.create(IItem.class);
    }

    public void obtenerAsync(final IServiceAsyncManager<ServiceResponse<List<ItemDto>>> serviceResultManager){
        mClient.obtener().enqueue(new Callback<List<ItemDto>>(){

            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {

                try {
                    serviceResultManager.onResponse(generarResponse(response));
                } catch (IOException e) {
                    serviceResultManager.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {
               serviceResultManager.onFailure(t.getMessage());
            }
        });
    }

    private ServiceResponse<List<ItemDto>> generarResponse(Response<List<ItemDto>> response) throws  IOException{
        ServiceResponse<List<ItemDto>> retorno = new ServiceResponse<>();
        retorno.setCodigo(response.code());
        retorno.setObj(response.body());
        retorno.setError(getErrorResponse(response));
        return retorno;
    }
}
