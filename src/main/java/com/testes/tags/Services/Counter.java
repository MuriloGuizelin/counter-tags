package com.testes.tags.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class Counter {

    public Map<String, Integer> countHtmlTags(String url) throws IOException {
        Map<String, Integer> tagCountMap = new HashMap<>();

        // Faz a requisição para a URL e obtém o conteúdo HTML
        Document doc = Jsoup.connect(url).parser(Parser.htmlParser()).get();

        // Obtém todas as tags do HTML e conta a ocorrência de cada uma
        for (Element element : doc.getAllElements()) {
            String tagName = element.tagName();
            tagCountMap.put(tagName, tagCountMap.getOrDefault(tagName, 0) + 1);
        }

        return tagCountMap;
    }
}
