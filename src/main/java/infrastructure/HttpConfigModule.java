package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpConfigModule extends AbstractModule {
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {

    }

    @Provides
    GetMethod provideGetMethod() {

        String endpoint = "http://petstore-demo-endpoint.execute-api.com";
        return new GetMethod(endpoint);
    }
}
