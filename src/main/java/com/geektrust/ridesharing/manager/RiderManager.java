package com.geektrust.ridesharing.manager;

import com.geektrust.ridesharing.pojo.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {
    Map<String, Rider> riderMap = new HashMap<>();

    public void addRider(Rider rider) {
        riderMap.put(rider.getRiderId(), rider);
    }

    public Rider getRider(String riderId) {
        return riderMap.get(riderId);
    }

    public Map<String, Rider> getRiderMap() {
        return riderMap;
    }
}
