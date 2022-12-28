package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response =given()
                .when()
                .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);}
//1. Extract the All Ids
@Test
public void test001() {
    System.out.println("------------------StartingTest---------------------------");

    List<Integer> totalId = response.extract().path("id");
    System.out.println("Total records are : " + totalId);
    System.out.println("------------------End of Test---------------------------");
}
//2. Extract the all Names
@Test
public void test002() {
    System.out.println("------------------StartingTest---------------------------");

    List<String>listOfAllNames = response.extract().path("name");
    System.out.println("Total records are : " + listOfAllNames);
    System.out.println("------------------End of Test---------------------------");
}
//3. Extract the name of 5th object
@Test
public void test003() {
    System.out.println("------------------StartingTest---------------------------");

    String listname = response.extract().path("[4].name");
    System.out.println("Extract the name of 5th object" +listname);
    System.out.println("------------------End of Test---------------------------");
}
//4. Extract the names of all object whose status = inactive
@Test
public void test004() {
    System.out.println("------------------StartingTest---------------------------");

    List<?>listOfStatusinactive = response.extract().path("findAll{it.status=='inactive'}.name");
    System.out.println("Extract the name of 5th object" +listOfStatusinactive);
    System.out.println("------------------End of Test---------------------------");
}
//5. Extract the gender of all the object whose status = active
@Test
public void test005() {
    System.out.println("------------------StartingTest---------------------------");

    List<?>listOfStatusactive = response.extract().path("findAll{it.status=='active'}.gender");
    System.out.println("Extract the name of 5th object" +listOfStatusactive);
    System.out.println("------------------End of Test---------------------------");
}
//6. Print the names of the object whose gender = female
@Test
public void test006() {
        List<?> allObjectWithGenderFemale = response.extract().path("findAll{it.gender=='female'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allObjectWithGenderFemale);
        System.out.println("------------------End of Test---------------------------");

}
//7. Get all the emails of the object where status = inactive
public void test7() {
    List<?> allEmailsOfStatusInactive = response.extract().path("findAll{it.status=='inactive'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Email Of Inactive : " + allEmailsOfStatusInactive);
    System.out.println("------------------End of Test---------------------------");

}

//8. Get the ids of the object where gender = male
@Test
public void test8() {
    List<?> allIdWithGenderMale = response.extract().path("findAll{it.gender=='male'}.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Gender is Female: " + allIdWithGenderMale);
    System.out.println("------------------End of Test---------------------------");

}
//9. Get all the status
@Test
public void test009() {
    List<String> allStatus = response.extract().path("status");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the status  : " + allStatus);
    System.out.println("------------------End of Test---------------------------");

}
    //10. Get email of the object where name = Chandrakin Johar
    @Test
    public void test10() {
        List<HashMap <String,?>>  emailsOfChandrakinJohar = response.extract().path("findAll{it.name == 'Chandrakin Johar'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Chandrakin Johar : " + emailsOfChandrakinJohar);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get gender of id = 5393
    @Test
    public void test11() {
        List<?> getGenderOfId5393 = response.extract().path("findAll{it.id == '5393'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender Of Id 5393 : " + getGenderOfId5393);
        System.out.println("------------------End of Test---------------------------");

    }

}
