package infrastructure;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import sample.GuicyRule;
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
    // REW: better to move this into guice rather than junit rule ?
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8091));

    @Rule
    public GuicyRule guicyRule = new GuicyRule(
        HttpPetModule.class,
        WireMockHttpConfigModule.class);

    @Inject
    private FetchPet testSubject;

    private String id;

    @Test
    public void shouldMakeHttpRequest() throws Exception {

        id = "2";
        configureWireMock();

        Pet pet = testSubject.fetch(id);

        assertThat(pet.getId(), is(id));
        assertThat(pet.getType(), is("dog"));
        assertThat(pet.getPrice(), is(new BigDecimal("249.99")));
    }

    // REW: can we get this into guice as well ?
    private void configureWireMock() {

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

//        int portOverride = wireMockRule.port();
//        return new HttpFetchPet(portOverride);
    }
}