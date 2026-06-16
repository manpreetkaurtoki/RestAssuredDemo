package rshttpcalls;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://report-ease-prj-666-naa.vercel.app";
	}

}
