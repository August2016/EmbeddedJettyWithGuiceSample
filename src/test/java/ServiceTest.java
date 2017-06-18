import org.junit.Before;
import org.junit.Test;
import sample.Domain;
import sample.FetchObject;
import sample.PetRepository;
import sample.SaveDomainObjectRepository;
import sample.Service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServiceTest {
    private Service testSubject;
    private String id = "xyz";
    private PetRepository repository;

    @Before
    public void shouldAcceptFetchObject() {

        FetchObject fetchObject = new FetchObject();
        Domain domain = new Domain(id);
        fetchObject.willFetch(domain);

        repository = new SaveDomainObjectRepository();

        testSubject = new Service(fetchObject, repository);
    }

    @Test
    public void shouldProcessById() {
        testSubject.process(id);

        assertThat(repository.get("xyz").getId(), is(id));
    }
}
