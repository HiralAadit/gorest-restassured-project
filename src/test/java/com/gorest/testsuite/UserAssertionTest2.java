package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class UserAssertionTest2 {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void totalIsEqual() {
        //response.body("id",equalTo(20));
        // response.extract().path("id");
        List<Object> idTotal = response.extract().path("id");
        System.out.println(idTotal.size());
        response.body("idTotal.size()",equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Soma Desai”
    @Test
    public void test2() {
        response.body("[2].name",equalTo("Soma Desai"));
    }

    //3. Check the single ‘Name’ in the Array list (Soma Desai)
    @Test
    public void test3() {
        response.body("[2].name",equalTo("Soma Desai"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Soma Desai, Bhisham Nambeesan, Chakrika Kocchar,Vrinda Talwar)
    @Test
    public void multipleNames() {
        response.body("name", CoreMatchers.hasItems("Soma Desai","Bhisham Nambeesan","Chakrika Kocchar","Vrinda Talwar"));
    }

    //5. Verify the email of userid = 5405 is equal “kocchar_chakrika@boehm.net”
    @Test
    public void verifyEmail() {
        response.body("[4].email",equalTo("kocchar_chakrika@boehm.net"));
    }

    //6. Verify the status is “Active” of username is “Amrit Ahuja”
    @Test
    public void test6() {
        response.body("[7].status",equalTo("active"));
    }

    //7. Verify the Gender = male of "Vimala Kakkar"
    @Test
    public void test7() {
        response.body("[11].gender",equalTo("male"));
    }

}