package sample;

public class SaveDomainObjectRepository implements PetRepository {

    private Domain object;

    public Domain get(String xyz) {

        return object;
    }

    public void save(Domain d) {
        object = d;
    }
}
