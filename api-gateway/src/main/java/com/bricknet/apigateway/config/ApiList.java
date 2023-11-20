package com.bricknet.apigateway.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiList {


    static String[] admin = new String[] {"/user/profile/addProfile/**", "/user/profile/addListOfProfile/**", "", "/suggestionPost/api/verification/**", "/api/broadcasting/**", "/communityPost/admin/**"};

    static String[] openApi = new String[] {"/eureka/**","super-admin/swagger-ui/index.html", "/auth/**, /auth/login/**",
            "/communityPost/post/**", "/send/**", "/api/to-do/**", "/media/**", "/suggestionPost/api/suggestions/**"
    };

//    public static final ArrayList<String> adminApiList = (ArrayList<String>) Arrays.asList(admin);
//
//    public static final ArrayList<String> openApiList = (ArrayList<String>) Arrays.asList(openApi);

}
