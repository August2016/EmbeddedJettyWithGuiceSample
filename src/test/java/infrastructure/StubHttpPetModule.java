package infrastructure;

import com.google.inject.AbstractModule;

public class StubHttpPetModule extends AbstractModule {
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {

        bind(FetchPet.class).to(StubFetchPet.class);
    }
}
