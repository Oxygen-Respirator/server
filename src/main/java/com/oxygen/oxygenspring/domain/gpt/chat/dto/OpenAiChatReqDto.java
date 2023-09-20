package com.oxygen.oxygenspring.domain.gpt.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

@Schema(hidden = true)
public class OpenAiChatReqDto {
    @NotNull
    private String model;

    @NotNull
    private List<Message> messages;

    private List<Function> functions;

    private Double temperature;

    private Double topP;

    private Integer n;

    private Boolean stream;

    private Object stop;

    private Integer maxTokens;

    private Double presencePenalty;

    private Double frequencyPenalty;

    private Map<String, Object> logitBias;

    private String user;


    public static class Message {

        @NotNull
        private String role;

        private String content;

        private String name;

        private FunctionCall functionCall;

    }

    public static class FunctionCall {

        @NotNull
        private String name;

        @NotNull
        private String arguments;

    }


    public static class Function {

        @NotNull
        private String name;

        private String description;

        @NotNull
        private Parameters parameters;

        private String functionCall;

    }

    public static class Parameters {
        @NotNull
        private String type;

        private Map<String, Object> properties;
    }
}


