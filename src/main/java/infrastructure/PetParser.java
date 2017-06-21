package infrastructure;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import sample.Pet;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class PetParser {

    private final JsonFactory jsonFactory;

    @Inject
    PetParser(JsonFactory jsonFactory) {

        this.jsonFactory = jsonFactory;
    }

    Pet parseResponse(InputStream stream) throws IOException {

        JsonParser parser = jsonFactory.createParser(stream);

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

        Pet pet = builder.build();
        parser.close();
        return pet;
    }

    public void write(Pet pet, ServletOutputStream outputStream) {

        try {
            JsonGenerator generator = jsonFactory.createGenerator(outputStream);
            generator.writeStartObject();
            generator.writeStringField("id", pet.getId());
            generator.writeStringField("type", pet.getType());
            generator.writeNumberField("price", pet.getPrice());
            generator.writeEndObject();
            generator.close();
        } catch (IOException e) {
            try {
                outputStream.write("Could not write json".getBytes());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
