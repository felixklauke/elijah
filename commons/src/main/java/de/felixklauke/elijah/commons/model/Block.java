package de.felixklauke.elijah.commons.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@Data
public class Block {

    private static final String HASH_DELIMITER = "|";

    private final int id;
    private final long currentTimeStamp = System.currentTimeMillis();
    private final List<Transaction> transactions;
    private final int proof;
    private final String previousHash;

    public String getHash() {
        return DigestUtils.sha256Hex(id + HASH_DELIMITER
                + currentTimeStamp + HASH_DELIMITER
                + transactions.toString() + HASH_DELIMITER
                + proof + HASH_DELIMITER
                + previousHash
        );
    }
}
