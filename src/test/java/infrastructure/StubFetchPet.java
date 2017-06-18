package infrastructure;

import sample.Pet;

public class StubFetchPet implements FetchPet {

    @Override
    public Pet fetch(String id) {
        return new Pet(id);
    }
}
