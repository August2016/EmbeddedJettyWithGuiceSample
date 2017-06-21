package infrastructure;

import com.google.inject.Inject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import sample.Pet;

import java.io.IOException;
import java.io.InputStream;

public class HttpFetchPet implements FetchPet {

    private final GetMethod get;
    private final PetParser petParser;

    static final String PATH = "/petstore/pets/";

    @Inject
    HttpFetchPet(GetMethod get, PetParser petParser) {
        this.get = get;
        this.petParser = petParser;
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

                return petParser.parseResponse(responseBodyAsStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
