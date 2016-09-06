package com.comparaonline.web.services;

import com.comparaonline.beans.Deck;
import com.comparaonline.exceptions.WrongServerResponseException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JSONClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONClientService.class);
    private static final String URL_COMPARA_ONLINE = "http://dealer.internal.comparaonline.com:8080";
    private static final int DECK_SIZE = 52;

    public String getDeckToken() throws WrongServerResponseException {
        Client client = Client.create();
        WebResource webResource = client.resource(String.format("%s/deck", URL_COMPARA_ONLINE));
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new WrongServerResponseException(String.format("Failed : HTTP error code : %s", response.getStatus()));
        }
        return response.getEntity(String.class);
    }

    private Deck getDeckFromServer(final String token) throws WrongServerResponseException, IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(String.format("%s/deck/%s/deal/%s", URL_COMPARA_ONLINE, token, DECK_SIZE));
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new WrongServerResponseException(String.format("Failed : HTTP error code : %s", response.getStatus()));
        }
        String entity = response.getEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(entity);
        return new Deck(node);
    }

    private Deck getLocalDeck() {
        return Deck.createLocal();
    }

    public Deck getDeck(final String token) {
        Deck deck = null;
        try {
            deck = getDeckFromServer(token);
        } catch (WrongServerResponseException | IOException e) {
            LOGGER.warn("Problem getting deck from server.", e);
        }
        if (deck == null) {
            deck = getLocalDeck();
        }
        return deck;
    }

    public static void main(final String[] args) throws WrongServerResponseException {
        JSONClientService service = new JSONClientService();
        String token = service.getDeckToken();
        System.out.println(String.format("RESPONSE TOKEN: %s", token));
        System.out.println(String.format("DECK: %s", service.getDeck(token)));
    }
}
