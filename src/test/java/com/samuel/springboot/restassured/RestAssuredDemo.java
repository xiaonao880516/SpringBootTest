package com.samuel.springboot.restassured;

import com.alibaba.fastjson.JSONObject;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.samuel.springboot.entity.LoginUser;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class RestAssuredDemo {

    /**
     * 注册解析器
     */
    @Before
    public void init(){
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    /**
     * 验证返回状态码
     */
    @Test
    public void testStatusCode() {
        int statusCode = get("http://www.baidu.com/").statusCode();
        assertEquals(200, statusCode);
    }

    /**
     * 验证json字段结果
     */
    @Test
    public void testJsonResponse() {
        Response response = given()
                .when()
                .get("http://localhost:8080/getUser/38");

        List<LoginUser> gameList = JSONObject.parseArray(response.body().asString(), LoginUser.class);
        Assert.assertEquals(gameList.get(0).getId(), 38);
        Assert.assertEquals(gameList.get(0).getUsername(), "admin");
    }

    /**
     * 验证json返回格式
     */
    @Test
    public void testJsonSchema() {
        // Given
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
        // When
        get("http://localhost:8080/getJsonObject/38").then().assertThat().body(matchesJsonSchemaInClasspath("products-schema-test.json").using(jsonSchemaFactory));

    }

    /**
     * 验证json返回结果
     */
    @Test
    public void testConditions() {
        get("http://localhost:8080/getJsonObject/38")
                .then().statusCode(200)
                .body("id", response -> equalTo(38))
                .body("username", equalTo("admin"))
                .body("real_name", endsWith("员"))
                .time(lessThan(5000L)); // Milliseconds, 验证返回时间
    }

    /**
     * Groovy语法测试
     */
    @Test
    public void testGroovy() {
        get("http://localhost:8080/getUser")
                .then().statusCode(200)
                .body("username.findAll{it.length()>7}", hasItems("shichang", "18610233588"))
                .body("findAll{it.username.length()>7}.username", hasItems("shichang", "18610233588"))
                .body("username.collect{it.length()}.sum()", greaterThan(50))
                .body("username.collect{it.length()}.max()", greaterThan(10))
                .body("username.collect{it.length()}.min()", greaterThanOrEqualTo(4));
    }

    /**
     * 兰超测试
     */
    @Test
    public void testLanChao() {
        Response loginResponse= given()
                .formParam("username", "13810567325")
                .formParam("password","123456a")
                .request()
                .header("Accept","application/json, text/plain, */*")
                .post("http://beta.lchapp_api.youxinger.net/frontStage/login/login");

        String tid = loginResponse.getHeader("tid");



        given()
                .log().all()
                .header("Accept","application/json, text/plain, */*")
                .header("tid",tid)
                .queryParam("phone", "13810567325")
                .get("http://pre.lchapp_api.youxinger.net/frontStage/vip/search-byphone")
                .then()
                .statusCode(200)
                .body("data.real_name", hasItems("孟伟"));


//        System.out.println(searchResponse.body().asString());
    }
}
