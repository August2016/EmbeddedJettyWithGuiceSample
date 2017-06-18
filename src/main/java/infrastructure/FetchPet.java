package infrastructure;

import sample.Pet;

public interface FetchPet {
    Pet fetch(String id);
}
