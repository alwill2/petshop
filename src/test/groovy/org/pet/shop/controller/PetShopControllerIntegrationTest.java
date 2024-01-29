package org.pet.shop.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.pet.shop.dto.PetDto;
import org.pet.shop.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetShopControllerIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetShopControllerIntegrationTest.class);

    @LocalServerPort
    private int port;

    private static final TestRestTemplate restTemplete = new TestRestTemplate();

    private static HttpHeaders headers = new HttpHeaders();

    @Autowired
    private static PetRepository petRepository;

    @BeforeAll
    public static void setup(){
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
    }

    @Test
    public void testPostPet()
    {
        String createPetJson =
                "{\n" +
                "  \"name\": \"Katy Purry\",\n" +
                "  \"type\": \"cat\",\n" +
                "  \"color\": \"black\",\n" +
                "  \"parents\" : {\n" +
                "    \"mother\": \"917cb6b1-60c3-44d6-b30d-baa0ca2fa132\",\n" +
                "    \"father\": \"f9275923-a3de-460a-a9bd-d6341185adef\"\n" +
                "  }\n" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(createPetJson,headers);
        ResponseEntity<PetDto> postResponse = restTemplete.exchange(
                "http://localhost:" + port + "/pets",
                HttpMethod.POST,request,PetDto.class);

        // Verify post
        assertEquals("wrong http status", HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull("no pet data in response", postResponse.getBody());
        PetDto createdPet = postResponse.getBody();
        assertNotNull("created pet must have id",createdPet.getId());
        assertEquals("wrong pet name", "Katy Purry", createdPet.getName());
        assertEquals("wrong pet color", "black", createdPet.getColor());
        assertEquals("wrong pet father", "f9275923-a3de-460a-a9bd-d6341185adef", createdPet.getParents().getFather());
    }

    @Test
    public void testGetPet()
    {
        PetDto createdPet = createPet();
        HttpEntity<String> request = new HttpEntity<>(null,headers);
        ResponseEntity<PetDto> getPetResponse = restTemplete.exchange(
                "http://localhost:" + port + "/pets/" + createdPet.getId(),
                HttpMethod.GET,request,PetDto.class);

        assertEquals("wrong http status", HttpStatus.OK, getPetResponse.getStatusCode());
        assertNotNull("no pet data in response", getPetResponse.getBody());
        PetDto petReturned = getPetResponse.getBody();
        assertEquals("wrong pet id", createdPet.getId(), petReturned.getId());
        assertEquals("wrong pet name", createdPet.getName(), petReturned.getName());
        assertEquals("wrong pet color", createdPet.getColor(), petReturned.getColor());
        assertEquals("wrong pet type", createdPet.getType(), petReturned.getType());
        assertEquals("wrong pet father", createdPet.getParents().getFather(), petReturned.getParents().getFather());
        assertEquals("wrong pet mother", createdPet.getParents().getMother(), petReturned.getParents().getMother());
    }

    @Test
    public void testGetPetWithInvalidPetId()
    {
        HttpEntity<String> request = new HttpEntity<>(null,headers);
        ResponseEntity<?> getPetResponse = restTemplete.exchange(
                "http://localhost:" + port + "/pets/" + UUID.randomUUID(),
                HttpMethod.GET,request,String.class);
        // Verify Get Invalid Pet Id
        assertEquals("wrong http status", HttpStatus.BAD_REQUEST, getPetResponse.getStatusCode());

    }

    @Test
    public void testUpdatePet(){
        PetDto createdPet = createPet();
        String newMother = UUID.randomUUID().toString();
        String newFather = UUID.randomUUID().toString();

        String updatePetJson =
                "{\n" +
                        "  \"id\": \""+createdPet.getId()+"\",\n" +
                        "  \"name\": \"Puppy bl\",\n" +
                        "  \"type\": \"dog\",\n" +
                        "  \"color\": \"white\",\n" +
                        "  \"parents\" : {\n" +
                        "    \"mother\": \""+newMother+"\",\n" +
                        "    \"father\": \""+newFather+"\"\n" +
                        "  }\n" +
                        "}";
        HttpEntity<String> request = new HttpEntity<>(updatePetJson,headers);
        ResponseEntity<PetDto> putResponse = restTemplete.exchange(
                "http://localhost:" + port + "/pets",
                HttpMethod.PUT,request,PetDto.class);

        // Verify PUT
        assertEquals("wrong http status", HttpStatus.OK, putResponse.getStatusCode());
        assertNotNull("no pet data in response", putResponse.getBody());
        PetDto updatedPet = putResponse.getBody();
        assertEquals("updated pet must have same id",createdPet.getId(), updatedPet.getId());
        assertEquals("wrong pet name", "Puppy bl", updatedPet.getName());
        assertEquals("wrong pet color", "white", updatedPet.getColor());
        assertEquals("wrong pet father", newFather, updatedPet.getParents().getFather());
        assertEquals("wrong pet mother", newMother, updatedPet.getParents().getMother());
    }

    private PetDto createPet() {
        String createPetJson =
                "{\n" +
                        "  \"name\": \"Katy Purry\",\n" +
                        "  \"type\": \"cat\",\n" +
                        "  \"color\": \"black\",\n" +
                        "  \"parents\" : {\n" +
                        "    \"mother\": \"917cb6b1-60c3-44d6-b30d-baa0ca2fa132\",\n" +
                        "    \"father\": \"f9275923-a3de-460a-a9bd-d6341185adef\"\n" +
                        "  }\n" +
                        "}";
        HttpEntity<String> request = new HttpEntity<>(createPetJson,headers);
        ResponseEntity<PetDto> postResponse = restTemplete.exchange(
                "http://localhost:" + port + "/pets",
                HttpMethod.POST,request,PetDto.class);
        return postResponse.getBody();
    }
}
