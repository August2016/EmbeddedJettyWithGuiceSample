package infrastructure;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.inject.Inject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import sample.Pet;

import java.io.IOException;
import java.io.InputStream;

public class HttpFetchPet implements FetchPet {

    private final GetMethod get;
    private final JsonFactory jsonFactory = new JsonFactory();
    static final String PATH = "/petstore/pets/";

    @Inject
    HttpFetchPet(GetMethod get) {

        this.get = get;
    }

    @Override
    public Pet fetch(String id) {

        // build the request uri
        // String uri = requestUri + PATH + id;
        get.setPath(PATH + id);


        try {
            HttpClient httpClient = new HttpClient();
            int responseCode = httpClient.executeMethod(get);
            if (responseCode == 200) {
                InputStream responseBodyAsStream = get.getResponseBodyAsStream();

                return parseResponse(responseBodyAsStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // REW: this belongs in a separate class
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
