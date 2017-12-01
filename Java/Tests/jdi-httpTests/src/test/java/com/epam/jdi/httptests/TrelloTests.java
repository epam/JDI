package com.epam.jdi.httptests;

import com.epam.http.requests.*;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;
import static org.hamcrest.core.IsEqual.equalTo;

public class TrelloTests {

    @Test
    public void createNewBoardTest() {
        init(TrelloApi.class);
        String boardName = "Lorem ipsum board";

        RequestData data = new RequestData()
                .set(d -> d.body = "{\"name\": \"" + boardName + "\"}");

        RestResponse response = TrelloApi
                .boardsPost
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);

        response
                .assertThat()
                    .body("name", equalTo(boardName));
    }

    @Test
    public void getBoardById() {
        init(TrelloApi.class);
        String boardId = "5a1d6074ddd2e876b1d87a4e";

        RequestData data = new RequestData()
                .set(d -> d.pathParams
                        .add(boardId));

        RestResponse response = TrelloApi
                .getBoardById
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);
        response.assertThat()
                .body("id", equalTo(boardId));
    }

    @Test
    public void getBoardCardsList() {
        init(TrelloApi.class);
        String boardId = "5a1d6074ddd2e876b1d87a4e";

        RequestData data = new RequestData()
                .set(d -> d.pathParams
                        .add(boardId));

        RestResponse response = TrelloApi
                .getBoardCardsList
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);
    }

    @Test
    public void getCardByShortId() {
        init(TrelloApi.class);

        RequestData data = new RequestData()
                .set(d -> d.pathParams
                        .add("5a1d6074ddd2e876b1d87a4e"))
                .set(d -> d.pathParams
                        .add("1"));

        RestResponse response = TrelloApi
                .getBoardCardById
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);
        response.assertThat()
                .body("name", equalTo("Нужно дописать jdi-http"));
    }

    @Test
    public void postNewCommentToCard() {
        init(TrelloApi.class);

        RequestData data = new RequestData()
                  .set(d -> d.pathParams
                        .add("5a1d7bb2fa880a3c112d6174"))
                  .set(d -> d.body = "{\"text\": \" Lorem ipsum dolor sit amet, consectetur adipiscing elit \"}");

        RestResponse response = TrelloApi
                .postNewCommentToCard
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);
    }

    @Test
    public void getAllUserBoards() {
        init(TrelloApi.class);

        RestResponse restResponse = TrelloApi
                .getAllMemberBoards
                .call()
                .assertStatus(200, ResponseStatusType.OK);
    }

    @Test
    public void getCardByUniqueId() {
        init(TrelloApi.class);

        RequestData data = new RequestData()
                .set(d -> d.queryParams
                        .add(new QueryParameter("fields", "url")))
                .set(d -> d.queryParams
                        .add(new QueryParameter("fields", "shortUrl")))
                .set(d -> d.pathParams
                        .add("5a1d7bb2fa880a3c112d6174"));

        RestResponse restResponse = TrelloApi
                .getCardByUniqueId
                .call(data)
                .assertStatus(200, ResponseStatusType.OK);
    }
}
