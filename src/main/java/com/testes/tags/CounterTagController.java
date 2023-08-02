package com.testes.tags;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class CounterTagController {

    private final CounterTagService tagCounterService;
    private final CounterTagRepository counterTagRepository;

    public CounterTagController(CounterTagService tagCounterService, CounterTagRepository counterTagRepository) {
        this.tagCounterService = tagCounterService;
        this.counterTagRepository = counterTagRepository;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("urlsForm", new CounterTagForm());
        
        List<CounterTagEntity> counterTagEntities = counterTagRepository.findAll();
        model.addAttribute("counterTagEntities", counterTagEntities);
        
        return "index";
    }

    @PostMapping("/countTags")
    public String countTags(@ModelAttribute CounterTagForm urlsForm, Model model) {
        List<String> urls = Arrays.asList(urlsForm.getUrls().split("\n"));
        List<CounterTagDTO> results = tagCounterService.countHtmlTags(urls);
        model.addAttribute("results", results);

        for (CounterTagDTO result : results) {
            List<CounterTagEntity> existingEntities = counterTagRepository.findByUrl(result.getUrl());
            for (String tagName : result.getTagCountMap().keySet()) {
                int tagCount = result.getTagCountMap().get(tagName);
                if (!existingEntities.isEmpty()) {
                    CounterTagEntity existingEntity = existingEntities.get(0);
                    existingEntity.setTagCount(tagCount);
                    counterTagRepository.save(existingEntity);
                } else {
                    CounterTagEntity tagEntity = new CounterTagEntity(result.getUrl(), tagName, tagCount);
                    counterTagRepository.save(tagEntity);
                }
            }
        }

        // Atualizar a lista de informações do banco de dados após salvar os dados
        List<CounterTagEntity> counterTagEntities = counterTagRepository.findAll();
        model.addAttribute("counterTagEntities", counterTagEntities);

        return "results";
    }
    
}
