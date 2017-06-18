package sample;

public class PetService {
    private final FetchPet fetchPet;
    private final Repository repository;

    public PetService(FetchPet fetchPet, Repository repository) {

        this.fetchPet = fetchPet;
        this.repository = repository;
    }

    public void process(String xyz) {
        Pet d = fetchPet.fetch(xyz);
        repository.save(d);
    }
}
