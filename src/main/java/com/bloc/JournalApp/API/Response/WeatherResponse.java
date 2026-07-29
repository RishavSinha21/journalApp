package com.bloc.JournalApp.API.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class WeatherResponse{

    private Current current;

    @Getter
    @Setter
    public static class Current{
        private String observation_time;
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;

    }
}




