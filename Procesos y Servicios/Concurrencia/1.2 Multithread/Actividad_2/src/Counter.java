public class Counter {
    int count;

    public Counter() {
        this.count = 0;
    }
    
    public int getCount() {
        return count;
    }

    public synchronized void increase(){
        count += 1;
    }


}
