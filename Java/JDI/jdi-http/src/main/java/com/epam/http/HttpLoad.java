package com.epam.http;

import java.util.Map;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
public class HttpLoad {

    public static PerformanceResult LoadService(long liveTimeMSec, HttpRequest... requests) {
        Random rnd = new Random();
        long start = currentTimeMillis();
        PerformanceResult pr = new PerformanceResult();
        int Length = requests.length;
        do { pr.addResult(requests[rnd.nextInt(Length)].get());
        } while (currentTimeMillis() - start < liveTimeMSec);
        return pr;
    }
    public static PerformanceResult LoadService(HttpRequest... requests) {
        return LoadService(5000, requests);
    }
    public static PerformanceResult LoadService(long liveTimeMSec, Map<HttpRequest, Integer> weightRequests) {
        Random rnd = new Random();
        long start = currentTimeMillis();
        PerformanceResult pr = new PerformanceResult();
        int Length = getLength(weightRequests);
        do { pr.addResult(getRequest(weightRequests, Math.round(rnd.nextFloat()*Length)).get());
        } while (currentTimeMillis() - start < liveTimeMSec);
        return pr;
    }
    public static PerformanceResult LoadService(Map<HttpRequest, Integer> weightRequests) {
        return LoadService(5000, weightRequests);
    }
    private static int getLength(Map<HttpRequest, Integer> wightRequests) {
        int Length = 0;
        for(Map.Entry<HttpRequest, Integer> pair : wightRequests.entrySet())
            Length += pair.getValue();
        return Length;
    }
    private static HttpRequest getRequest(Map<HttpRequest, Integer> wightRequests, int num) {
        if (wightRequests == null || wightRequests.size() == 0)
            return null;
        int Sum = 0;
        for(Map.Entry<HttpRequest, Integer> pair : wightRequests.entrySet()) {
            Sum += pair.getValue();
            if (Sum >= num)
                return pair.getKey();
        }
        return null;
    }

}
