package org.events;

import java.util.ArrayList;
import java.util.List;

//----------------- Event Handler that fires the dataChange events --------------
//This class needs to be static since you need to register all your classes that want to be notified of data change events
public class DataChangedHandler {
 private static List<DataChangeListenter> listeners = new ArrayList<DataChangeListenter>();

 public static void registerDataChangeListener(DataChangeListenter listener) {
     listeners.add(listener);
 }

 public static void fireDataChange(DataChangeEvent dataChangeEvent) {
     for(DataChangeListenter listenter : listeners) {
         listenter.dataChangeEvent(dataChangeEvent);
     }
 }


}