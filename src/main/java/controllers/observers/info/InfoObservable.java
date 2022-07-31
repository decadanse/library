package controllers.observers.info;

import model.Info;

import java.util.ArrayList;
import java.util.List;

public class InfoObservable {
    private static List<InfoObserver> infoObservers = new ArrayList<>();

    public static void registerInfoObserver(InfoObserver infoObserver){
    	infoObservers.add(infoObserver);
    }

    public static void onInfoAdded(Info info){
        for (InfoObserver infoObserver : infoObservers) {
        	infoObserver.onInfoAdded(info);
        }
    }

}
