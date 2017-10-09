package com.epam.http;

import com.epam.http.requests.RestMethod;

import java.util.Map;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
public class RestLoad {

    public static PerformanceResult loadService(long liveTimeMSec, RestMethod... requests) {
        Random rnd = new Random();
        int Length = requests.length;
        PerformanceResult pr = new PerformanceResult();
        //pr.addResult(requests[0].get());
        long start = currentTimeMillis();
        do { pr.addResult(requests[rnd.nextInt(Length)].GET());
        } while (currentTimeMillis() - start < liveTimeMSec);
        return pr;
    }
    public static PerformanceResult loadService(RestMethod... requests) {
        return loadService(5000, requests);
    }
    public static PerformanceResult loadService(long liveTimeMSec, Map<RestMethod, Integer> weightRequests) {
        Random rnd = new Random();
        long start = currentTimeMillis();
        PerformanceResult pr = new PerformanceResult();
        int Length = getLength(weightRequests);
        do { pr.addResult(getRequest(weightRequests, Math.round(rnd.nextFloat()*Length)).GET());
        } while (currentTimeMillis() - start < liveTimeMSec);
        return pr;
    }
    public static PerformanceResult loadService(Map<RestMethod, Integer> weightRequests) {
        return loadService(5000, weightRequests);
    }
    private static int getLength(Map<RestMethod, Integer> wightRequests) {
        int Length = 0;
        for(Map.Entry<RestMethod, Integer> pair : wightRequests.entrySet())
            Length += pair.getValue();
        return Length;
    }
    private static RestMethod getRequest(Map<RestMethod, Integer> wightRequests, int num) {
        if (wightRequests == null || wightRequests.size() == 0)
            return null;
        int Sum = 0;
        for(Map.Entry<RestMethod, Integer> pair : wightRequests.entrySet()) {
            Sum += pair.getValue();
            if (Sum >= num)
                return pair.getKey();
        }
        return null;
    }

}
