package sample;

import com.google.inject.AbstractModule;
import infrastructure.InMemoryPetRepository;
import infrastructure.PetRepository;

public class PetModule extends AbstractModule {
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {

        bind(PetRepository.class).to(InMemoryPetRepository.class);
    }
}
