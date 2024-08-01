package com.tinqinacademy.commentsservice.api;

public class RestApiRoutes {

    public static final String API = "/api/v1";

    public static final String API_HOTEL = API+"/hotel";
    public static final String API_SYSTEM = API+"/system";



    public static final String GET_ALL_ROOM_COMMENTS = API_HOTEL+"/{roomId}/comment";
    public static final String ADD_COMMENT_FOR_ROOM = API_HOTEL+"/{roomId}/comment";
    public static final String EDIT_COMMENT_FOR_ROOM = API_HOTEL+"/comment/{commentId}";



    public static final String ADMIN_EDIT_COMMENT_FOR_ROOM = API_SYSTEM+"/comment/{commentId}";
    public static final String ADMIN_DELETE_COMMENT_FOR_ROOM = API_SYSTEM+"/comment/{commentId}";

}