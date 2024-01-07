package com.techin.library.book.search.library.book.search.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(String top_work, String name){}
