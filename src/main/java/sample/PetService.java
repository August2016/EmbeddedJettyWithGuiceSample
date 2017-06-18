package sample;

import com.google.inject.Inject;
import infrastructure.FetchPet;
import infrastructure.PetRepository;

public class PetService {
    private final FetchPet fetchPet;
    private final PetRepository repository;

    @Inject
    public PetService(FetchPet fetchPet, PetRepository repository) {

        this.fetchPet = fetchPet;
        this.repository = repository;
    }

    public void process(String xyz) {
        Pet d = fetchPet.fetch(xyz);
        repository.save(d);
    }
}
