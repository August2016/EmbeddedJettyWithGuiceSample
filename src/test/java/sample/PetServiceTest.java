package sample;

import com.google.inject.Inject;
import infrastructure.PetRepository;
import infrastructure.StubHttpPetModule;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PetServiceTest {

    @Rule
    public GuicyRule guicyRule = new GuicyRule(PetModule.class, StubHttpPetModule.class);

    private String id = "2";

    @Inject
    private PetRepository repository;

    @Inject
    private PetService testSubject;

    @Test
    public void shouldProcessById() {
        testSubject.process(id);

        assertThat(repository.get(id).getId(), is(id));
    }
}
