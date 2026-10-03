package com.example.order_service.Config;

import com.example.order_service.Exception.ProductNotFoundException;
import com.example.order_service.Exception.UsersPrincipalNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;


public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default() ;


    @Override
    public Exception decode(String key, Response response) {

        if(response.status() == 404){
            if(key.contains("ProductClient")){
                return  new ProductNotFoundException("Product do not exists for the given Id");
            }
            if(key.contains("UserClient")){
                return new UsersPrincipalNotFoundException("User do not exists for the give Id");
            }
        }


        return defaultErrorDecoder.decode(key,response);
    }
}
