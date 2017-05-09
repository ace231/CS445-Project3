/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445.project3;

/**
 *
 * @author Armando Sanabria <asanabria@cpp.edu>
 */
class Block {

    private boolean isActive;
    private final BlockType type;
    private float x;
    private float y;
    private float z;

    /*
    Enumeration of block types
     */
    public enum BlockType {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5);
        private int blockID;

        /**
         *
         * @param i
         */
        BlockType(int i) {
            blockID = i;
        }

        /**
         *
         * @return
         */
        public int getID() {
            return blockID;
        }

        /**
         *
         * @param i
         */
        public void setID(int i) {
            blockID = i;
        }
    }

    /**
     *
     * @param type
     */
    public Block(BlockType type) {
        this.type = type;
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return type.getID();
    }
}