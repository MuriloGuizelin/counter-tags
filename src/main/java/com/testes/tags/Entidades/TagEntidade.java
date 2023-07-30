package com.testes.tags.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TagEntidade {

    @Id
    private String tagName;

    private int tagCount;

    // Construtores (pode ser gerado pelo lombok ou criado manualmente)

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagCount() {
        return tagCount;
    }

    public void setTagCount(int tagCount) {
        this.tagCount = tagCount;
    }
}
