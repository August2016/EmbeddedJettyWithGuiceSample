package sample;

public interface PetRepository {
    Domain get(String xyz);

    void save(Domain d);
}
