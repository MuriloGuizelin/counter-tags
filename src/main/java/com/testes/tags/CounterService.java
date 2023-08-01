package com.testes.tags;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CounterService {

    public List<TagDTO> countHtmlTags(List<String> urls) {
        List<TagDTO> urlTagInfoList = new ArrayList<>();

        for (String url : urls) {
            try {
                Map<String, Integer> tagCountMap = new HashMap<>();
                Document doc = Jsoup.connect(url).parser(Parser.htmlParser()).get();

                // Obtém todas as tags do HTML e conta a ocorrência de cada uma
                for (Element element : doc.getAllElements()) {
                    String tagName = element.tagName();
                    tagCountMap.put(tagName, tagCountMap.getOrDefault(tagName, 0) + 1);
                }

                urlTagInfoList.add(new TagDTO(url, tagCountMap));
            } catch (IOException e) {
                // Em caso de erro ao fazer a requisição ou processar a URL, podemos adicionar uma entrada vazia
                urlTagInfoList.add(new TagDTO(url, new HashMap<>()));
            }
        }

        return urlTagInfoList;
    }
}