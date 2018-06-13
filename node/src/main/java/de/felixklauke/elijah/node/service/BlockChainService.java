package de.felixklauke.elijah.node.service;

import de.felixklauke.elijah.commons.model.Block;
import de.felixklauke.elijah.commons.model.BlockChain;
import de.felixklauke.elijah.commons.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@Service
public class BlockChainService {

    private static final UUID NODE_ID = UUID.randomUUID();

    /**
     * The block chain itself.
     */
    private final BlockChain blockChain = new BlockChain();

    /**
     * Contains all current transactions.
     */
    private final Queue<Transaction> currentTransactions = new ConcurrentLinkedQueue<>();

    /**
     * Create the given transaction and save it.
     *
     * @param transaction The transaction
     *
     * @return The id of the next block.
     */
    public int createTransaction(Transaction transaction) {
        currentTransactions.offer(transaction);

        return blockChain.getLastBlock().getId() + 1;
    }

    public Block mineBlock() {
        int proof = blockChain.workProof();

        Transaction transaction = new Transaction("Miner Agent", NODE_ID);
        createTransaction(transaction);

        String previousHash = blockChain.getLastBlock().getHash();

        return createBlock(previousHash, proof);
    }

    private Block createBlock(String previousHash, int proof) {
        UUID chainId = blockChain.getChainId();
        int lastBlockId = blockChain.getLastBlock().getId();

        Block block = new Block(lastBlockId, new ArrayList<>(currentTransactions), proof, previousHash);

        boolean success = blockChain.addBlock(block, chainId);

        return success ? block : null;
    }


    public BlockChain getBlockChain() {
        return blockChain;
    }
}
