package totalhashmap;

import java.util.HashMap;


//The class supports only "get" and "put"
public class TotalHashMap<T,U>{

      private HashMap<T,U> items = new HashMap<T,U>();
      private U defaultValue;

      //the constructor requires a default value to be returned when
      //key is not found in map
      public TotalHashMap(U defaultValue)
      {
              this.defaultValue = defaultValue;
      }

      //note that the default value has not been inserted in the HashMap
      public U get(T key)
      {
              if (items.containsKey(key))
                      return items.get(key);
              else
                      return defaultValue;
      }

      //note the function returns the previous value associated with key
      // and null if no such value existed
      public U put(T key, U value)
      {
              return items.put(key,value);
      }
}