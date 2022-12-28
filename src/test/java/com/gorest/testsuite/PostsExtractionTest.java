package com.gorest.testsuite;

import com.gorest.model.PostsPojo;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        System.out.println("------------------StartingTest---------------------------");

        List<Object> totalRecord = response.extract().path("record");
        System.out.println("Total records are : " + totalRecord.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15the record
    @Test
    public void test3() {
        String body = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Body of 15th Record : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test4() {
        System.out.println("------------------StartingTest---------------------------");

        List<Object> userId = response.extract().path("user_id");
        System.out.println("Total records are : " + userId);//.size()
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test5() {
        System.out.println("------------------StartingTest---------------------------");

        List<Object> title = response.extract().path("title");
        System.out.println("Total Title are : " + title);//.size()
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test6() {
        List<?> allRecords = response.extract().path("findAll{it.user_id==5448}.record");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("UserId Record : " + allRecords);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Extract the body of all records whose id = 2671
    @Test

    public void test7() {
        List<?> allRecordWhoseID2670 = response.extract().path("findAll{it.id==2670}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("UserId Record Body : " + allRecordWhoseID2670);
        System.out.println("------------------End of Test---------------------------");


    }

//    @Test
//    public void createUsers() {
//        List<String> userList = new ArrayList<>();
//        userList.add("selenium");
//        userList.add("API");
//        PostsPojo postsPojo = new PostsPojo();
//        postsPojo.setId(1221);
//        postsPojo.setUser_id(5511);
//        postsPojo.setTitle("learn postman");
//        postsPojo.setBody("Title is matched");
//        Response response = given().log().all()
//                .header("Content-Type", "application/json")
//                .when()
//                .body(postsPojo)
//                .post();
//        response.prettyPrint();
//        response.then().log().all().statusCode(201);
//
//    }
}
