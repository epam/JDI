package com.epam.jdi.httptests;

import com.epam.http.requests.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
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
        String boardName = "Lorem ipsum board " + RandomStringUtils.random(12, true, true);

        RequestData data = new RequestData()
                .set(d -> d.body = "{\"name\": \"" + boardName + "\"}");

        RestResponse response = TrelloApi
                .boardsPost
                .call(data)
                .isOk();

        response
                .assertThat()
                    .body("name", equalTo(boardName));
    }

    @Test
    public void getBoardById() {
        RequestData data = new RequestData()
                .set(d -> d.pathParams.add(BOARD_ID));

        RestResponse response = TrelloApi
                .getBoardById
                .call(data)
                .isOk();
        response.assertThat()
                .body("id", equalTo(BOARD_ID));
    }

    @Test
    public void getBoardCardsList() {
        RequestData data = new RequestData()
                .set(d -> d.pathParams.add(BOARD_ID));

        RestResponse response = TrelloApi
                .getBoardCardsList
                .call(data)
                .isOk();

        response.assertThat()
                .body("name.size()", equalTo(6));
    }

    @Test
    public void getCardByShortId() {
        RequestData data = new RequestData()
                .set(d -> { d.pathParams.add(BOARD_ID);
                            d.pathParams.add("1");});

        RestResponse response = TrelloApi
                .getBoardCardById
                .call(data)
                .isOk();
        response.assertThat()
                .body("name", equalTo("Lorem ipsum dolor sit amet"));
    }

    @Test
    public void postNewCommentToCard() {
        String newComment = "New comment" + RandomStringUtils.random(7, true, false);

        RequestData data = new RequestData()
                .set(d -> { d.pathParams.add(CARD_UNIQUE_ID);
                            d.body = "{\"text\": \"" + newComment + "\"}";});

        RestResponse response = TrelloApi
                .postNewCommentToCard
                .call(data)
                .isOk();
        response.assertThat()
                .body("data.text", containsString(newComment));

    }

    @Test
    public void getAllUserBoards() {
        RequestData data = new RequestData()
                .set(d -> d.pathParams.add("jdiframwork"));

        RestResponse restResponse = TrelloApi
                .getAllMemberBoards
                .call(data)
                .isOk();

        restResponse.assertThat()
                .body("name.size()", greaterThan(4));
    }

    @Test
    public void getCardByUniqueId() {
        RequestData data = new RequestData()
                .set(d -> { d.queryParams.add("fields", "url,shortUrl");
                            d.pathParams.add(CARD_UNIQUE_ID);});

        RestResponse restResponse = TrelloApi
                .getCardByUniqueId
                .call(data)
                .isOk();

        restResponse
                .assertThat()
                .body("url", containsString("https://trello.com/c/SSFPAlkB/1-lorem-ipsum-dolor-sit-amet"))
                .body("shortUrl", containsString("https://trello.com/c/SSFPAlkB"))
                .body("id", equalTo(CARD_UNIQUE_ID))
                .body("keySet().size()", is(3));
    }
}
