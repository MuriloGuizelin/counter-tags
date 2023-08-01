package com.testes.tags;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class CounterController {

    private final CounterService tagCounterService;
    private final CounterTagRepository counterTagRepository;

    public CounterController(CounterService tagCounterService, CounterTagRepository counterTagRepository) {
        this.tagCounterService = tagCounterService;
        this.counterTagRepository = counterTagRepository;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("urlsForm", new TagForm());
        return "index";
    }

    @PostMapping("/countTags")
    public String countTags(@ModelAttribute TagForm urlsForm, Model model) {
        List<String> urls = Arrays.asList(urlsForm.getUrls().split("\n"));
        List<TagDTO> results = tagCounterService.countHtmlTags(urls);
        model.addAttribute("results", results);

        // Save the tag counts to the database
        for (TagDTO result : results) {
            for (String tagName : result.getTagCountMap().keySet()) {
                int tagCount = result.getTagCountMap().get(tagName);
                CounterTagEntity tagEntity = new CounterTagEntity();
                tagEntity.setTagName(tagName);
                tagEntity.setTagCount(tagCount);
                counterTagRepository.save(tagEntity);
            }
        }

        return "results";
    }
}
