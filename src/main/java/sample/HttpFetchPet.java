package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.io.InputStream;

public class HttpFetchPet implements FetchPet {
    @Override
    public Pet fetch(String id) {


        GetMethod get = new GetMethod("http://petstore-demo-endpoint.execute-api.com/petstore/pets/" + id);

        HttpClient httpClient = new HttpClient();
        try {
            int respnseCode = httpClient.executeMethod(get);
            if (respnseCode == 200) {
                InputStream responseBodyAsStream = get.getResponseBodyAsStream();
                JsonFactory jsonFactory = new JsonFactory();
                JsonParser parser = jsonFactory.createParser(responseBodyAsStream);

                if (parser.nextToken() != JsonToken.START_OBJECT) {
                    throw new IOException("Expected Start Object, but found " + parser.getText());
                }

                Pet.Builder builder = Pet.newBuilder();

                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if (fieldName.equals("id")) {
                        builder.id(parser.getValueAsString());
                    } else if (fieldName.equals("type")) {
                        builder.type(parser.getValueAsString());
                    } else if (fieldName.equals("price")) {
                        builder.price(parser.getDecimalValue());
                    }
                    // ignore unknown field names
                }

                parser.close();
                return builder.build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            URL url = new URL("http://petstore-demo-endpoint.execute-api.com");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application-json");
//            con.setConnectTimeout(30000);
//            con.setReadTimeout(30000);
//
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("param1", "val");
//
//            con.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(con.getOutputStream());
//            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
//            out.flush();
//            out.close();
//
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//
//            System.out.println(inputLine);
//
//            con.disconnect();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

        return null;
    }
}
