package sample;

import infrastructure.FetchPet;
import infrastructure.PetRepository;
import infrastructure.Repository;
import infrastructure.StubFetchPet;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PetServiceTest {
    private PetService testSubject;
    private String id = "1";
    private Repository repository;

    @Before
    public void shouldAcceptFetchObject() {

        // this is an external resource dependent class so we stub it
        FetchPet fetchPet = new StubFetchPet();

        repository = new PetRepository();

        testSubject = new PetService(fetchPet, repository);
    }

    @Test
    public void shouldProcessById() {
        testSubject.process(id);

        assertThat(repository.get(id).getId(), is(id));
    }
}
