package sample;

import java.util.HashMap;
import java.util.Map;

public class PetRepository implements Repository {

    private Map<String, Pet> petMap = new HashMap<>(2);

    public Pet get(String id) {

        return petMap.get(id);
    }

    public void save(Pet pet) {
        petMap.put(pet.getId(), pet);
    }
}
