package springidol;

public class Stage {

    private Stage() {
    }

    private static class StageSingletonHolder {
        static Stage instance = new Stage();
    }

    public static Stage getInstance() {
        return StageSingletonHolder.instance;
    }
    
    public void performance(Performer performer) throws PerformanceException {
        performer.perform();
    }
}
