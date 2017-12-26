import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BeanUtils {
    /**
     *      * Scans object "from" for all getters. If object "to"
     *      * contains correspondent setter, it will invoke it
     *      * to set property value for "to" which equals to the property
     *      * of "from".
     *      * <p/>
     *      * The type in setter should be compatible to the value returned
     *      * by getter (if not, no invocation performed).
     *      * Compatible means that parameter type in setter should
     *      * be the same or be superclass of the return type of the getter.
     *      * <p/>
     *      * The method takes care only about public methods.
     *      *
     *      * @param to   Object which properties will be set.
     *      * @param from Object which properties will be used to get values.
     *      
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        List<Method> listTo = methods("set", to);
        List<Method> listFrom = methods("get", from);
        Iterator<Method> iterator = listTo.iterator();
        for (Method methodFrom : listFrom) {
            if (iterator.hasNext())
                iterator.next().invoke(to, methodFrom.invoke(from, null));
        }
    }

    private static List<Method> methods(String name, Object o) {
        List<Method> list = new ArrayList<>();
        Class clazz = o.getClass();
        Method[] methods = clazz.getMethods();
        String s;
        for (Method method : methods) {
            String s2 = "";
            s = method.getName();
            for (int i = 0; i < s.length(); i++) {
                s2 += s.charAt(i);
                if (s2.equals(name)) {
                    list.add(method);
                    break;
                }
            }
        }
        return list;
    }
}
