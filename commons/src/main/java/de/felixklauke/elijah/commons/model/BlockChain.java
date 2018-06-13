package de.felixklauke.elijah.commons.model;

import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class BlockChain {

    private static final String VALIDATION_PREFIX = "00000";

    @Getter
    private final UUID chainId = UUID.randomUUID();

    private final Deque<Block> blocks = new ConcurrentLinkedDeque<>();

    public List<Block> getContent() {
        return new ArrayList<>(blocks);
    }

    public long getLength() {
        return blocks.size();
    }

    public Block getLastBlock() {
        return blocks.getLast();
    }

    public int workProof() {
        return workProof(getLastBlock().getProof());
    }

    private int workProof(int lastProof) {
        int proof = 0;

        while (!validate(lastProof, proof)) {
            proof++;
        }

        return proof;
    }

    private boolean validate(int lastProof, int currentProof) {
        String result = lastProof + "" + currentProof;
        return DigestUtils.sha256Hex(result).startsWith(VALIDATION_PREFIX);
    }

    public boolean addBlock(Block block, UUID chainId) {
        if (!this.chainId.equals(chainId)) {
            return false;
        }

        return blocks.add(block);
    }
}
