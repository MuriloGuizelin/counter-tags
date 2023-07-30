package com.testes.tags.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testes.tags.Services.Counter;

import java.io.IOException;
import java.util.Map;

@RestController
public class TagControlador {

    private final Counter tagCounterService;

    @Autowired
    public TagControlador(Counter tagCounterService) {
        this.tagCounterService = tagCounterService;
    }

    @GetMapping("/countTags")
    public Map<String, Integer> countTags(@RequestParam String url) throws IOException {
        return tagCounterService.countHtmlTags(url);
    }
}
