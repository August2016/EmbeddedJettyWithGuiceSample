package infrastructure;

import sample.Pet;

public interface PetRepository {
    Pet get(String xyz);

    void save(Pet d);
}
