package com.epam.jdi.httptests;

import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.*;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.TrelloApi.*;
import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class TrelloTests {

    public static final String BOARD_ID = "5a27e3b62fef5d3a74dca48a";
    public static final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";

    @BeforeClass
    public void initService() {
        init(TrelloApi.class);
    }

    @Test
    public void createNewBoardTest() {
        String boardName = "Lorem ipsum board " + random(12, true, true);
        RestResponse response = boardsPost
                .call(requestBody(format("{\"name\": \"%s\"}", boardName)));
        response.isOk().body("name", equalTo(boardName));
    }

    @Test
    public void getBoardById() {
        RestResponse response = getBoardById
                .call(requestParams("board_id", BOARD_ID));
        response.isOk().body("id", equalTo(BOARD_ID));
    }

    @Test
    public void getBoardCardsList() {
        getBoardCardsList.call(requestParams("board_id", BOARD_ID))
            .isOk().body("name.size()", equalTo(6));
    }

    @Test
    public void getCardByShortId() {
        getBoardCardById.call(requestParams(new Object[][] {{"board_id", BOARD_ID}, {"short_card_id", "1"}}))
            .isOk().assertThat().body("name", equalTo("Lorem ipsum dolor sit amet"));
    }

    @Test
    public void postNewCommentToCard() {
        String newComment = "New comment" + random(7, true, false);
        RestResponse response = postNewCommentToCard
            .call(requestData(d -> {
                d.pathParams.add("card_id", CARD_UNIQUE_ID);
                d.body = format("{\"text\": \"%s\"}", newComment);}
            ));
        response.isOk()
            .body("data.text", containsString(newComment));
    }

    @Test
    public void getAllUserBoards() {
        RestResponse restResponse = getAllMemberBoards
            .call(requestParams("user_name", "jdiframwork"));
        restResponse.assertThat()
            .body("name.size()", greaterThan(4));
    }

    @Test
    public void getCardByUniqueId() {
        RestResponse restResponse = getCardByUniqueId
            .call(requestData(d -> {
                d.queryParams.add("fields", "url,shortUrl");
                d.pathParams.add("card_id", CARD_UNIQUE_ID);}));

        restResponse.assertBody(new Object[][]{
            {"url", containsString("https://trello.com/c/SSFPAlkB/1-lorem-ipsum-dolor-sit-amet")},
            {"shortUrl", containsString("https://trello.com/c/SSFPAlkB")},
            {"id", equalTo(CARD_UNIQUE_ID)},
            {"keySet().size()", is(3)}
        });
    }
}
