package dev.guillermosg.msmovements.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * AccountsResponse model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Cuenta> accounts;
}
