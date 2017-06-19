package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.commons.httpclient.methods.GetMethod;

public class WireMockHttpConfigModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FetchPet.class).to(HttpFetchPet.class);
    }

    @Provides
    GetMethod provideGetMethod() {

        String endpoint = "http://localhost:" + 8091;
        return new GetMethod(endpoint);
    }
}
