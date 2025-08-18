package com.devsShop.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {

    public static final String SUCCESS_MESSAGE = "성공적으로 조회되었습니다.";

    public static final String CURRENT_PAGE = "CURRENT_PAGE";
    public static final String PAGE_UNIT = "PAGE_UNIT";

    public static final long DEFAULT_PAGE_SIZE = 30;

    public static final String ACCESS_TOKEN = "ACCESS-TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH-TOKEN";
    public static final ObjectMapper objectMapper = new ObjectMapper();

}
