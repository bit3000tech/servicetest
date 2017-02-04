package com.bit3000.servicetest.services.base;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://dev.bit3000.com/";
    protected Retrofit retrofit;
    protected String mApiKey;

    public ServiceGenerator()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(getGsonConverter())
                .build();
    }


    private GsonConverterFactory getGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Double.class,DoubleTypeAdapter);
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    private static TypeAdapter<Number> DoubleTypeAdapter = new TypeAdapter<Number>() {

        @Override
        public void write(JsonWriter out, Number value)
                throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String result = in.nextString();
                if ("".equals(result)) {
                    return null;
                }
                return Double.parseDouble(result);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }
    };

    protected ErrorResponse getErrorResponse(Response response)
    {

        if(response == null)
        {
            return generateErrorResponse("Sin respuesta");
        }

        if(response.isSuccessful())
        {
            return null;
        }

        if(estadoBadGateway(response))
        {
            return generateErrorResponse("Bad Gateway");
        }

        try
        {
            Converter<ResponseBody, ErrorResponse> errorConverter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
            return errorConverter.convert(response.errorBody());
        }
        catch (IOException e)
        {
            return generateErrorResponse("Respuesta Incorrecta");
        }

    }

    private static ErrorResponse generateErrorResponse(String mensaje)
    {
        return new ErrorResponse("", mensaje);
    }

    protected boolean ejecucionCorrecta(Response response)
    {
        return estadoOk(response) || estadoCreado(response);
    }


    protected boolean estadoCreado(Response response)
    {
        return response.code() == HttpStatusCode.Created.value();
    }

    protected boolean estadoConflicto(Response response)
    {
        return response.code() == HttpStatusCode.Conflict.value();
    }

    protected boolean estadoOk(Response response)
    {
        return response.code() == HttpStatusCode.OK.value();
    }

    protected boolean estadoBadGateway(Response response)
    {
        return response.code() == HttpStatusCode.BadGateway.value();
    }

    protected boolean estadoNoContent(Response response)
    {
        return response.code() == HttpStatusCode.NoContent.value();
    }


}
