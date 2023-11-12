package dev.guillermosg.msclients.application.ports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Error dto.
 */
@Builder
@Getter
@Setter
public class ErrorDTO {

    @JsonProperty("cod_response")
    private String codResponse;

    @JsonProperty("des_response")
    private String desResponse;

}
