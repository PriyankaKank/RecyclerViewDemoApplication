package com.example.user.recyclerviewdemoapplication.Network;

import com.example.user.recyclerviewdemoapplication.Model.OutputParameter;
import com.example.user.recyclerviewdemoapplication.WebServiceConstants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 07-03-2019.
 * @author Priyanka Gole
 * Interface contains all the methods of web service calls
 */

public interface AuthenticationApi {

    @GET(WebServiceConstants.FACTS)
    Call<OutputParameter> getAllPost();
}
