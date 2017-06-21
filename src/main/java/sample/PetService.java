package sample;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import infrastructure.FetchPet;
import infrastructure.PetRepository;

@Singleton
public class PetService {
    private final FetchPet fetchPet;
    private final PetRepository repository;

    @Inject
    PetService(FetchPet fetchPet, PetRepository repository) {

        this.fetchPet = fetchPet;
        this.repository = repository;
    }

    public Pet process(String id) {

        Pet d = fetchPet.fetch(id);
        repository.save(d);
        return d;
    }
}
