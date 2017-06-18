package infrastructure;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import sample.Pet;

import java.io.IOException;
import java.io.InputStream;

public class HttpFetchPet implements FetchPet {

    private JsonFactory jsonFactory = new JsonFactory();
    private static final String HTTP_GET_PET = "http://${host}:${port}";
    static final String PATH = "/petstore/pets/";
    private String requestUri;

    HttpFetchPet(int portOverride) {
        requestUri = HTTP_GET_PET
            .replace("${host}", "localhost")
            .replace("${port}", ""+portOverride);
    }

    public HttpFetchPet() {
        requestUri = HTTP_GET_PET
            .replace("${host}", "petstore-demo-endpoint.execute-api.com")
            .replace("${port}", "");

    }


    @Override
    public Pet fetch(String id) {

        // build the request uri
        String uri = requestUri + PATH + id;

        GetMethod get = new GetMethod(uri);

        HttpClient httpClient = new HttpClient();
        try {
            int respnseCode = httpClient.executeMethod(get);
            if (respnseCode == 200) {
                InputStream responseBodyAsStream = get.getResponseBodyAsStream();

                return parseResponse(responseBodyAsStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Pet parseResponse(InputStream responseStream) throws IOException {

        JsonParser parser = jsonFactory.createParser(responseStream);

        if (parser.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected Start Object, but found " + parser.getText());
        }

        Pet.Builder builder = Pet.newBuilder();

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            mapObject(parser, builder);
        }

        Pet pet = builder.build();
        parser.close();
        return pet;
    }

    private void mapObject(JsonParser parser, Pet.Builder builder) throws IOException {

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
}
