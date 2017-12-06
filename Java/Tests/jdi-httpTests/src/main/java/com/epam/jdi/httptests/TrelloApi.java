package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloApi {

        public static final String BOARDS = "/boards";

        @ContentType(JSON) @GET(BOARDS)
        static RestMethod boardsGet;

        @ContentType(JSON) @POST(BOARDS)
        static RestMethod boardsPost;

        @ContentType(JSON) @GET("/boards/{board_id}")
        static RestMethod getBoardById;

        @ContentType(JSON) @GET("/boards/{board_id}/cards")
        static RestMethod getBoardCardsList;

        @ContentType(JSON) @GET("/boards/{board_id}/cards/{short_card_id}")
        static RestMethod getBoardCardById;

        @ContentType(JSON) @GET("/members/{user_name}/boards")
        static RestMethod getAllMemberBoards;

        @ContentType(JSON) @GET("/members")
        static RestMethod membersGet;

        @ContentType(JSON) @DELETE("/cards")
        static RestMethod deleteACardFromBoard;

        @ContentType(JSON) @POST("/cards/{card_id}/actions/comments")
        static RestMethod postNewCommentToCard;

        @QueryParameter(name = "test", value = "test")
        @ContentType(JSON) @GET("/cards/{card_id}")
        static RestMethod getCardByUniqueId;

}
