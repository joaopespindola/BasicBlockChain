package org.example;
import java.util.Date;
import java.security.MessageDigest;

public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String prevHash) {
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.prevHash = prevHash;
        //all created blocks will have a hash
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                prevHash + Long.toString(timeStamp) + data
        );
        return calculatedHash;
    }

    public void mineBlock(int DIFFICULTY_VAR){
        String target = new String(new char[DIFFICULTY_VAR]).replace('\0', '0');
        while(!hash.substring(0, DIFFICULTY_VAR).equals(target)){
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Block was mined: " + hash);

    }
}