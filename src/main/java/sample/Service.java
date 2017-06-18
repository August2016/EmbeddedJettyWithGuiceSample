package sample;

public class Service {
    private final FetchObject fetchObject;
    private final PetRepository repository;

    public Service(FetchObject fetchObject, PetRepository repository) {

        this.fetchObject = fetchObject;
        this.repository = repository;
    }

    public void process(String xyz) {
        Domain d = fetchObject.fetch(xyz);
        repository.save(d);
    }
}
