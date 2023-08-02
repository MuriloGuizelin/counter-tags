package com.testes.tags;

import java.util.Map;

public class CounterTagDTO {
    private String url;
    private Map<String, Integer> tagCountMap;

    public CounterTagDTO() {
    }

    public CounterTagDTO(String url, Map<String, Integer> tagCountMap) {
        this.url = url;
        this.tagCountMap = tagCountMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Integer> getTagCountMap() {
        return tagCountMap;
    }

    public void setTagCountMap(Map<String, Integer> tagCountMap) {
        this.tagCountMap = tagCountMap;
    }
}
