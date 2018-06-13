package de.felixklauke.elijah.node.controller;

import de.felixklauke.elijah.commons.model.Block;
import de.felixklauke.elijah.commons.model.BlockChain;
import de.felixklauke.elijah.commons.model.Transaction;
import de.felixklauke.elijah.node.model.BlockChainResponse;
import de.felixklauke.elijah.node.service.BlockChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlockChainController {

    private final BlockChainService blockChainService;

    @RequestMapping(path = "/transaction", method = RequestMethod.POST)
    public int createTransaction(@RequestBody Transaction transaction) {
        return blockChainService.createTransaction(transaction);
    }

    @RequestMapping(path = "/block/mine", method = RequestMethod.POST)
    public Block mineBlock() {
        return blockChainService.mineBlock();
    }

    @RequestMapping(path = "/block/chain", method = RequestMethod.GET)
    public BlockChainResponse getBlockChain() {
        BlockChain blockChain = blockChainService.getBlockChain();
        return BlockChainResponse.fromBlockChain(blockChain);
    }
}
