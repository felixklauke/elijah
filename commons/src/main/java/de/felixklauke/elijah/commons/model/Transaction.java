package de.felixklauke.elijah.commons.model;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@Data
@ToString
public class Transaction {

    private final String source;
    private final UUID nodeId;
}
