package com.bricknet.apigateway.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiList {


    static String[] admin = new String[] {"/user/profile/addProfile/**", "/user/profile/addListOfProfile/**", "/user/profile/allProfile",
            "/suggestionPost/api/verification/**", "/api/broadcasting/**", "/communityPost/admin/getAllPostNeedToVerified", "/communityPost/admin/postVerification/**",
            "/user/profile/updateProfile/**", "/user/profile/passwordUpdate",
    };

    static String[] openApi = new String[] {"/eureka/**", "/auth/**", "/communityPost/post/**", "/suggestionPost/api/suggestions/**", "/send/**", "/api/to-do/**", "/media/**"};

//    public static final ArrayList<String> adminApiList = (ArrayList<String>) Arrays.asList(admin);
//
//    public static final ArrayList<String> openApiList = (ArrayList<String>) Arrays.asList(openApi);

}
