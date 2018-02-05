package com.epam.http.test;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Created by Roman_Iovlev on 2/2/2018.
 */
public class Convert {
    private String json = "{\"productId\":\"886762471148\",\"productName\":\"KATHY-DRESS | 001 | WOVEN DRESS\",\"isMaster\":false,\"masterId\":\"30121459\",\"masterName\":\"KATHY DRESS\",\"variationAttributes\":{\"color\":{\"id\":\"001\",\"name\":\"BLACK\"},\"size\":{\"id\":\"0\",\"name\":\"0\"}},\"online\":true,\"searchable\":true,\"inStock\":false,\"price\":{\"salePrices_ToryBurchUS\":{\"currency\":\"USD\",\"price\":147.5},\"regularPrices_ToryBurchUS\":{\"currency\":\"USD\",\"price\":295}},\"images\":[\"TB_30121459_001\",\"TB_30121459_001_A\",\"TB_30121459_001_B\"],\"video\":\"\",\"saphid\":\"04010256\",\"bolHazMat\":false,\"styledWith\":[]}";
    public void convert() {
        JSONParser.quote(json);
    }
}
