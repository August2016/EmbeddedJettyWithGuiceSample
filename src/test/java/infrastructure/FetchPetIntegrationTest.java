package infrastructure;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import sample.Pet;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FetchPetIntegrationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private String id;

    @Test
    public void shouldMakeHttpRequest() throws Exception {

        id = "2";
        FetchPet testSubject = getFetchPetMock();

        Pet pet = testSubject.fetch(id);

        assertThat(pet.getId(), is(id));
        assertThat(pet.getType(), is("dog"));
        assertThat(pet.getPrice(), is(new BigDecimal("249.99")));
    }

    private FetchPet getFetchPetMock() {

        stubFor(get(urlEqualTo(HttpFetchPet.PATH + id))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\n" +
                    "  \"id\": 2,\n" +
                    "  \"type\": \"dog\",\n" +
                    "  \"price\": 249.99\n" +
                    "}")
            )
        );

        int portOverride = wireMockRule.port();
        return new HttpFetchPet(portOverride);
    }
}