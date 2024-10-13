package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

    private StringBuilder buffer ;
    private Integer beginIndex;
    private Integer endIndex;
    private Integer bufferBeginIndex;
    public SelectionImpl(){
        this.buffer=new StringBuilder();
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0; 

    }
    public SelectionImpl(StringBuilder buffer){
      this.buffer=buffer;
      this.beginIndex = 0;
      this.endIndex = 0;
      this.bufferBeginIndex = 0;   
    }
   /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return
     */
    public int getBeginIndex() {
        return beginIndex;
    }
     /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    public int getEndIndex() {
       return endIndex;
       
    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    public int getBufferBeginIndex() {
        return bufferBeginIndex;
        
    }
    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */

    public int getBufferEndIndex() {
        return bufferBeginIndex+buffer.length();
    }
     /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < 0 || beginIndex > buffer.length()) {
            throw new IndexOutOfBoundsException("Begin index out of bounds");
        }
        this.beginIndex=beginIndex;
        
    }
    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */

    public void setEndIndex(int endIndex) {
        if(endIndex<beginIndex){
            throw new IndexOutOfBoundsException("end index must be bigger than beginIndex");
        }
       this.endIndex=endIndex;
        
    }
    
}
