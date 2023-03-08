package com.studentweek15.studentinfo;

import com.studentweek15.constants.EndPoints;
import com.studentweek15.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSteps {
    @Step("getting all information :{0}")
    public ValidatableResponse getAllStudentInfo(){
        return   SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();

    }

    @Step("creating student with firstName :{0},lastName: {1},email:{2},programme:{3} and courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> coursesList) {


        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(coursesList);
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(studentPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Step("getting student info by firstName:{0}")
    public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
        String part1 = "findAll{it.firstName=='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract().path(part1 + firstName + part2);

    }


    @Step("update student infor with studentID:{0},firstName :{1}")
    public ValidatableResponse updateStudent(int studentId, String name, String lastName, String email, String firstName, List<String> courses) {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);


        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();


    }

    @Step("deleteing student information with studentId:{0}")
    public ValidatableResponse deleteStudentInfoByID(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("getting student info By studentId:{0}")
    public ValidatableResponse getStudentInfoByStudentId(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }


    @Step("getting student info by email:{0}")
    public HashMap<String,Object>getStudentInfoByEmail(String email){

        String part1 ="findAll{it.email=='";
        String part2 = "'}.get(0)";

        HashMap<String,Object>studentMapData =SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().statusCode(200).extract().path(part1+ email + part2);
        return studentMapData;
    }
}
