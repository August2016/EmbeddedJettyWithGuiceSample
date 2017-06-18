package sample;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PetServiceIntegrationTest {

    private PetService testSubject;
    private String id = "1";
    private Repository repository;

    @Before
    public void shouldAcceptFetchPet() {

        FetchPet fetchPet = new HttpFetchPet();

        repository = new PetRepository();

        testSubject = new PetService(fetchPet, repository);
    }

    @Test
    public void shouldProcessById() {
        testSubject.process(id);

        assertThat(repository.get(id).getId(), is(id));
        assertThat(repository.get(id).getType(), is("dog"));
    }
}
