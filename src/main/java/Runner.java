import infrastructure.FetchPet;
import infrastructure.HttpFetchPet;
import infrastructure.PetRepository;
import sample.PetService;

public class Runner {

    public static void main(String[] args) {

        FetchPet fetchPet = new HttpFetchPet();
        PetRepository petRepository = new PetRepository();
        PetService petService = new PetService(fetchPet, petRepository);
        petService.process("1");

    }
}
