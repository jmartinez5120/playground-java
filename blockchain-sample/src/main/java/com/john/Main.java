package com.john;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4);

        // Add some blocks to the blockchain
        blockchain.addBlock("Data to be transferred from cloud function A to cloud function B");
        blockchain.addBlock("Data to be transferred from cloud function B to cloud function C");

        // Print the blockchain
        for (Block block : blockchain.getBlockchain()) {
            System.out.println("Block " + block.getIndex());
            System.out.println("Data: " + block.getData());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Nonce: " + block.getNonce());
            System.out.println();
        }

        // Validate the blockchain
        System.out.println("Is blockchain valid? " + blockchain.isValid());
    }

}