package de.felixklauke.elijah.node.model;

import de.felixklauke.elijah.commons.model.Block;
import de.felixklauke.elijah.commons.model.BlockChain;
import lombok.Data;

import java.util.List;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@Data
public class BlockChainResponse {

    private final long chainLength;
    private final List<Block> blocks;

    public static BlockChainResponse fromBlockChain(BlockChain blockChain) {
        return new BlockChainResponse(blockChain.getLength(), blockChain.getContent());
    }
}
