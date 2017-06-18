package infrastructure;

import com.google.inject.AbstractModule;

public class HttpPetModule extends AbstractModule {
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {

        bind(FetchPet.class).to(HttpFetchPet.class);
    }
}
