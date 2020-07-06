package sk.fri.uniza.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import sk.fri.uniza.model.*;

import java.util.List;
import java.util.Map;

public interface DobrovolnaUloha2Service {
    @POST("/field")
    Call<ResponseBody> vytvorField(@Body FieldData field);

    @POST("/household/{householdID}/{fieldID}")
    Call<ResponseBody> posliData(@Path("householdID") String householdID,
                                         @Path("fieldID") String fieldID,
                                         @Body MojeData data);

}
