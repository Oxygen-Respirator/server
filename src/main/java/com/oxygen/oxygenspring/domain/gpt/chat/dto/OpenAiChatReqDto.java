package com.oxygen.oxygenspring.domain.gpt.chat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Schema(hidden = true)
public class OpenAiChatReqDto {
    @NotNull
    private String model;

    @NotNull
    private List<Message> messages;

//    private List<Function> functions;

    @JsonProperty("function_call")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String functionCall;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double temperature;

    @JsonProperty("top_p")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double topP;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer n;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean stream;

//    private Object stop;

    @JsonProperty("max_tokens")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxTokens;

    @JsonProperty("presence_penalty")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double presencePenalty;

    @JsonProperty("frequency_penalty")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double frequencyPenalty;

//    private Map<String, Object> logitBias;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String user;

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Message {

        @NotNull
        private String role;

        @NotNull
        private String content;


        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String name;

        @JsonProperty("function_call")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private FunctionCall functionCall;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class FunctionCall {

        @NotNull
        private String name;

        @NotNull
        private String arguments;

    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Function {

        @NotNull
        private String name;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;

        @NotNull
        private Parameters parameters;

    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Parameters {
        @NotNull
        private String type;

//        @JsonInclude(JsonInclude.Include.NON_NULL)
//        private Map<String, Object> properties;
    }
}


