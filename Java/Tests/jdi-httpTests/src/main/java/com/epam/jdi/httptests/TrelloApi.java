package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "7cf089da99f205d0fd828b6bdd295abf"),
        @QueryParameter(name = "token", value = "f552e4a551f39f8d2129bf4c58fd5bb55595471508408dd120def3537892ffad")
        })
public class TrelloApi {

        public static final String BOARDS = "/boards";
        public static final String BOARD_BY_ID = "/boards/{board_id}";

        public static final String BOARD_CARDS = "/boards/{board_id}/cards";
        public static final String BOARD_CARD_BY_ID = "/boards/{board_id}/cards/{short_card_id}";
        public static final String ADD_NEW_COMMENT_TO_CARD = "/cards/{card_id}/actions/comments";

        public static final String MEMBERS = "/members";
        public static final String USER_BOARDS_LIST = "/members/evgeniiaershova/boards";

        public static final String CARDS = "/cards";
        public static final String CARD_BY_ID = "/cards/{card_id}";


        @ContentType(JSON) @GET(BOARDS)
        static RestMethod boardsGet;

        @ContentType(JSON) @GET(BOARD_BY_ID)
        static RestMethod getBoardById;

        @ContentType(JSON) @GET(BOARD_CARDS)
        static RestMethod getBoardCardsList;

        @ContentType(JSON) @GET(BOARD_CARD_BY_ID)
        static RestMethod getBoardCardById;

        @ContentType(JSON) @GET(USER_BOARDS_LIST)
        static RestMethod getAllMemberBoards;

        @ContentType(JSON) @GET(MEMBERS)
        static RestMethod membersGet;

        @ContentType(JSON) @DELETE(CARDS)
        static RestMethod deleteACardFromBoard;

        @ContentType(JSON) @POST(BOARDS)
        static RestMethod boardsPost;

        @ContentType(JSON) @POST(ADD_NEW_COMMENT_TO_CARD)
        static RestMethod postNewCommentToCard;

        @QueryParameter(name = "test", value = "test")
        @ContentType(JSON) @GET(CARD_BY_ID)
        static RestMethod getCardByUniqueId;

}
