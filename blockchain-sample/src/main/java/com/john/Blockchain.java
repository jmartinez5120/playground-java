package com.john;

import java.util.ArrayList;

public class Blockchain {

    private ArrayList<Block> blockchain;
    private int difficulty;

    // Constructor
    public Blockchain(int difficulty) {
        this.blockchain = new ArrayList<>();
        this.difficulty = difficulty;
        addGenesisBlock();
    }

    // Add the genesis block (first block) to the chain
    private void addGenesisBlock() {
        Block genesisBlock = new Block(0, "Genesis Block", "0");
        blockchain.add(genesisBlock);
    }

    // Add a new block to the chain
    public void addBlock(String data) {
        Block latestBlock = blockchain.get(blockchain.size() - 1);
        Block newBlock = new Block(latestBlock.getIndex() + 1, data, latestBlock.getHash());
        mineBlock(newBlock, difficulty, 100);
        blockchain.add(newBlock);
    }

    // Mine a block with a given difficulty and maximum number of iterations
    private void mineBlock(Block block, int difficulty, int maxIterations) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        int iterations = 0;
        while (!block.getHash().substring(0, difficulty).equals(target)) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(block.calculateHash());
            iterations++;
            if (iterations >= maxIterations) {
                System.out.println("Mining failed. Max iterations reached.");
                break;
            }
        }
        if (iterations < maxIterations) {
            System.out.println("Block mined: " + block.getHash());
        }
    }

    // Validate the integrity of the blockchain
    public boolean isValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Invalid block hash.");
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Invalid previous block hash.");
                return false;
            }
        }
        return true;
    }

    // Getters
    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }
}
