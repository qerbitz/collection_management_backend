package com.example.project_transition.controller;

import com.google.gson.Gson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
@RequestMapping("/1")
public class ImageController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/upload")
    public String uploadImage() throws JsonProcessingException {


        final String uri = "https://api.imgbb.com/1/upload?key=4ddbd8e0aff381645594a058d20c206b";

        MultiValueMap<String, Object> image = new LinkedMultiValueMap<String, Object>();
        image.add("image", "iVBORw0KGgoAAAANSUhEUgAAAJAAAACQEAYAAAC31j77AAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dE////////CVj33AAAAAlwSFlzAAAASAAAAEgARslrPgAAAfxJREFUeNrt17FtWmEAhdH/BYtUHgCmwhu4o7UnQKzgjimyBlu4AurIFUj4ZQgXnxTOmeBWn3THAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAfmo6bo6b4+b6sjqsDqvD/KceBPz/LtvL9rKdXqbT9XQ9Xed5vVwv18t6FvAIzrfz7Xwb41c9BHhcAgRkBAjICBCQESAgI0BARoCAjAABGQECMgIEZAQIyAgQkBEgICNAQEaAgIwAARkBAjICBGQECMgIEJARICAjQEBGgICMAAEZAQIyAgRkBAjICBCQESAgI0BARoCAjAABGQECMgIEZAQIyAgQkBEgICNAQEaAgIwAARkBAjICBGQECMgIEJARICDz9P374/XjdYy/++f9876eAzyC793X7ms3xvT5OcYY81wPAh6PCwZkBAjICBCQESAgI0BARoCAjAABGQECMgIEZAQIyAgQkBEgICNAQEaAgIwAARkBAjICBGQECMgIEJARICAjQEBGgICMAAEZAQIyAgRkBAjICBCQESAgI0BARoCAjAABGQECMgIEZAQIyAgQkBEgICNAQEaAgIwAARkBAjICBGQECMgIEJARICAjQEDmaX5fvi3fxpju032613OARzAv5sW8qFcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP/IP474w2hD/BUUAAAAASUVORK5CYII=");


        ResponseEntity<String> response = restTemplate
                .postForEntity(uri, image, String.class);

        String responseJson = response.getBody();

        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
        JsonObject jsonImageUrl = jsonObject.getAsJsonObject("data");

        String imageUrl = jsonImageUrl.get("display_url").toString();
        String imageUrlFinal = imageUrl.substring(1, imageUrl.length()-1);


        System.out.println(imageUrlFinal);

        return imageUrlFinal;
    }
}

