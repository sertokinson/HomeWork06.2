import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Book book1=new Book();
        Book book2=new Book(1,"Книга");
        BeanUtils.assign(book1,book2);
        System.out.println(book1);
    }
}
