package infrastructure;

import sample.Pet;

public interface Repository {
    Pet get(String xyz);

    void save(Pet d);
}
