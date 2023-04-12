import java.util.function.Predicate;

public class MojPredykatOceny implements Predicate {


        @Override
        public boolean test(Object ob){
            if (!(ob instanceof Student))
                return false;

            Student student = (Student) ob;
            return student.getOcena() > 2;
        }
}
