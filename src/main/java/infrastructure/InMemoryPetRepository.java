package infrastructure;

import com.google.inject.Singleton;
import sample.Pet;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InMemoryPetRepository implements PetRepository {

    private Map<String, Pet> petMap = new HashMap<>(2);

    public Pet get(String id) {

        return petMap.get(id);
    }

    public void save(Pet pet) {
        System.out.println("saving pet " + pet.getType() + " to database");
        petMap.put(pet.getId(), pet);
    }
}
