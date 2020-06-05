package CommonActions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommonActions extends PageObject {
    private static JSONParser parser = new JSONParser();
    private static URL urlObject;
    private static StringBuffer response;
    private String token;
    private String ci = "client_id";
    private String cs = "client_secret";
    private String au = "audience";
    private String gt = "grant_type";
    private String school1 = "ZQ0ZbBhkvOv3f7rxEKPxq557nxygPeRn";
    private String school2 = "Id3A2TLE8mk_dcIbCj15M5oMvk5yA4Qn_8tCVGsNGj3fxvN1Q_6LKe3C0o2QBAZO";
    private String school3 = "https://realiseme-school-uat.eu.auth0.com/api/v2/";
    private String school4 = "client_credentials";
    private String admid = "538e52d0-a7c0-4e89-9b48-80f0d0ec958d";
    private String bookingLongId;
    private String bookingShortID;


    @Test
    public void getAccessTokenAuth0() throws IOException, ParseException {
//        JSONObject ReqBody = (JSONObject) parser.parse(new FileReader("src/test/resources/Files/ReqBody_school.json"));
        String url = "https://realiseme-school-uat.eu.auth0.com/oauth/token";
        urlObject = new URL(url);
//        String body = ReqBody.toJSONString();
        String body = "{\n \"" + ci + "\":\"" + school1 + "\",\n" +
                "            \"" + cs + "\":\"" + school2 + "\",\n" +
                "            \"" + au + "\":\"" + school3 + "\",\n" +
                "            \"" + gt + "\":\"" + school4 + "\"\n" +
                "    }";
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
            token = jsonObject.get("access_token").toString();
            System.out.println(token);
        }
    }


    //    @Test
    public void createBookingUsingRequestAPI(List<String> list) throws IOException, ParseException {
        getAccessTokenAuth0();
        String body = "{\"operationName\":\"createBooking\",\"variables\":{\"input\":{" +
                list.get(0) + "," +
                list.get(1) + "," +
                list.get(2) + "," +
                list.get(3) + "," +
                list.get(4) + "," +
                list.get(5) + "," +
                list.get(6) + "," +
                list.get(7) + "," +
                list.get(8) + "," +
                list.get(9) + "," +
                list.get(10) + "," +
                list.get(11) + "," +
                list.get(12) + "}}," +
                "\"query\":\"mutation createBooking($input: CreateBookingPayload!) {\\n  createBooking(input: $input) {\\n    id\\n    short_id\\n    name\\n    description\\n    __typename\\n  }\\n}\\n\"}";
        String url = "https://29cwhlvcb3.execute-api.us-east-1.amazonaws.com/uat/graphql";
        urlObject = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/json");
        connection.setRequestProperty("authorization", token);
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String encoding = connection.getContentEncoding();
            String responseLine = null;
            encoding = encoding == null ? "UTF-8" : encoding;
            String respbody = IOUtils.toString(connection.getInputStream(), encoding);
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject jsonObject = (JSONObject) parser.parse(respbody);
            JSONObject jsonObject2 = (JSONObject) parser.parse(jsonObject.get("data").toString());
            JSONObject jsonObject3 = (JSONObject) parser.parse(jsonObject2.get("createBooking").toString());
            bookingLongId = jsonObject3.get("id").toString();
            bookingShortID = jsonObject3.get("short_id").toString();
            Serenity.getCurrentSession().addMetaData("bookingLongId", bookingLongId);
            Serenity.getCurrentSession().addMetaData("bookingShortID", bookingShortID);
            Serenity.getCurrentSession().addMetaData("bookingShortIDforTimesheets", bookingShortID);
            System.out.println("bookingLongId = " + bookingLongId);
            System.out.println("bookingShortID = " + bookingShortID);
        }
    }


    public void acceptBookingAsClearedTeacherUsingRequestAPI(List<String> list) throws
            IOException, ParseException {
        getAccessTokenAuth0();
        String body = "{\"operationName\":\"acceptingBookingTeacher\",\"variables\":\n" +
                "            {\"payload\":{" +
                list.get(0) + "," + "\"bookingId\":\"" +
                Serenity.getCurrentSession().getMetaData().get("bookingLongId") + "\"," +
                list.get(1) + "}}," +
                " \"query\":\"mutation acceptingBookingTeacher($payload: BookingPayload!) {\\n  userAcceptedBooking(input: $payload)\\n}\\n\"}";
        String url = "https://29cwhlvcb3.execute-api.us-east-1.amazonaws.com/uat/graphql";
        urlObject = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/json");
        connection.setRequestProperty("authorization", token);
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String encoding = connection.getContentEncoding();
            String responseLine = null;
            encoding = encoding == null ? "UTF-8" : encoding;
            String respbody = IOUtils.toString(connection.getInputStream(), encoding);
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject jsonObject = (JSONObject) parser.parse(respbody);
            System.out.println(jsonObject.toString());
        }
    }

}
