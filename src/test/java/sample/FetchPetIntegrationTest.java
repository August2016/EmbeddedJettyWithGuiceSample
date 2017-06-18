package sample;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FetchPetIntegrationTest {

    @Test
    public void shouldMakeHttpRequest() throws Exception {

        FetchPet testSubject = new HttpFetchPet();

        Pet pet = testSubject.fetch("1");

        assertThat(pet.getId(), is("1"));
        assertThat(pet.getType(), is("dog"));
        assertThat(pet.getPrice(), is(new BigDecimal("249.99")));
    }
}