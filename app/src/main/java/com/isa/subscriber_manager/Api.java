package com.isa.subscriber_manager;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api
{
    @FormUrlEncoded
    @POST("add_subscriber.php")
    Call<ResponseModel> addSubscriber(
            @Field("msisdn") String msisdn,
            @Field("iccid") String iccid,
            @Field("charging_type") String charging_type,
            @Field("sim_profile_id") String sim_profile_id,
            @Field("service_type") String service_type,
            @Field("mvno_name") String mvno_name,
            @Field("tariff_code") String tariff_code,
            @Field("tac") String tac,
            @Field("brand") String brand,
            @Field("model") String model
    );

    @GET("get_subscribers.php")
    Call<List<Subscriber>>getSubscribers();

    @DELETE("delete_subscriber.php")
    Call<ResponseModel> deleteSubscriber(@Query("msisdn") String msisdn);

    @FormUrlEncoded
    @POST("update_subscriber.php")
    Call<ResponseModel> updateSubscriber(
            @Field("msisdn") String msisdn,
            @Field("iccid") String iccid,
            @Field("charging_type") String chargingType,
            @Field("sim_profile_id") String simProfileId,
            @Field("service_type") String serviceType,
            @Field("mvno_name") String mvnoName,
            @Field("tariff_code") String tariffCode,
            @Field("tac") String tac,
            @Field("brand") String brand,
            @Field("model") String model
    );
}