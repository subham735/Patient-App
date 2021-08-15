package com.subham.PatientApp.Cosntants;

import java.util.HashMap;
import java.util.Map;

public class RedisConstants {
    public static final String PATIENT_INFO_CACHE = "subham.patient_info";

    public static final Map<String,Integer> CACHE_MAP = new HashMap<String, Integer>(){
        {
            put(PATIENT_INFO_CACHE,  86400000); // 1day
        }
    };

}
