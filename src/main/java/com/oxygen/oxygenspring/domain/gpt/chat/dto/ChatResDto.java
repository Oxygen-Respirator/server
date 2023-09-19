package com.oxygen.oxygenspring.domain.gpt.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatResDto {
    @JsonProperty("usage")
    private Usage usage;
    @JsonProperty("choices")
    private List<Choices> choices;
    @JsonProperty("model")
    private String model;
    @JsonProperty("created")
    private int created;
    @JsonProperty("object")
    private String object;
    @JsonProperty("id")
    private String id;

    public static class Usage {
        @JsonProperty("total_tokens")
        private int totalTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("prompt_tokens")
        private int promptTokens;
    }

    public static class Choices {
        @JsonProperty("finish_reason")
        private String finishReason;
        @JsonProperty("message")
        private Message message;
        @JsonProperty("index")
        private int index;
    }

    public static class Message {
        @JsonProperty("content")
        private String content;
        @JsonProperty("role")
        private String role;
    }
}
