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
public class CounterTagService {

    public List<CounterTagDTO> countHtmlTags(List<String> urls) {
        List<CounterTagDTO> urlTagInfoList = new ArrayList<>();

        for (String url : urls) {
            try {
                Map<String, Integer> tagCountMap = new HashMap<>();
                Document doc = Jsoup.connect(url).parser(Parser.htmlParser()).get();

                for (Element element : doc.getAllElements()) {
                    String tagName = element.tagName();
                    tagCountMap.put(tagName, tagCountMap.getOrDefault(tagName, 0) + 1);
                }

                urlTagInfoList.add(new CounterTagDTO(url, tagCountMap));
            } catch (IOException e) {
                urlTagInfoList.add(new CounterTagDTO(url, new HashMap<>()));
            }
        }

        return urlTagInfoList;
    }
}
