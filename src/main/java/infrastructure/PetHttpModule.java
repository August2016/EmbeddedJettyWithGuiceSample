package infrastructure;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class PetHttpModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FetchPet.class).to(HttpFetchPet.class);
    }

    @Provides
    JsonFactory createJsonFactory() {

        return new JsonFactory();
    }

    @Provides
    PetParser createPetParser(JsonFactory jsonFactory) {

        return new PetParser(jsonFactory);
    }
}
