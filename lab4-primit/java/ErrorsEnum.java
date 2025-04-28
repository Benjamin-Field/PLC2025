import java.util.Scanner;
import java.util.EnumSet;

public class ErrorsEnum
{
    enum Error { FP_ROUNDING, FP_OVERFLOW, FP_UNDERFLOW, INT_OVERFLOW }

    enum Result { A_BIT_DIFFERENT, INFINITY, ZERO, VERY_DIFFERENT }
    
    private static <E extends Enum<E>> E getEnumElement(String elementTypeName, Class<E> elementType)
    {
        boolean haveResult = false;
        E result = null;
        Scanner stdin = new Scanner(System.in);
        
        while ( ! haveResult )
        {
            System.out.print("Input " + elementTypeName + ": ");
            try
            {
                result = Enum.valueOf(elementType, stdin.next().toUpperCase());
                haveResult = true;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Not a valid " + elementTypeName + ".");
                stdin.nextLine(); // skip the invalid input
            }
        }
        
        stdin.close();
        return result;
    }
  
    private static Result error2Result(Error e)
    {
        Result result = null;
        
        switch (e) {
        case FP_ROUNDING:
            result = Result.A_BIT_DIFFERENT;
            break;
        case FP_OVERFLOW:
            result = Result.INFINITY;
            break;
        case FP_UNDERFLOW:
            result = Result.ZERO;
            break;
        case INT_OVERFLOW:
            result = Result.VERY_DIFFERENT;
            break;
        }
        
        return result;
    }

    private static Error result2Error(Result e){
        Error result = null;

        switch(e){
        case A_BIT_DIFFERENT:
            result = Error.FP_ROUNDING;
            break;

        case INFINITY:
            result = Error.FP_UNDERFLOW;
            break;

        case ZERO:
            result = Error.FP_UNDERFLOW;
            break;
        
        case VERY_DIFFERENT:
            result = Error.INT_OVERFLOW;
            break;
        }
        return result;
    }
    

    public static void main(String[] args)
    {
        System.out.print("Known Results = ");
        for (Result e : EnumSet.allOf(Result.class)) 
        {
            System.out.print(e + " ");
        }
        System.out.println();
        
        Result e = getEnumElement("result", Result.class);
        System.out.println(e + " results in: " + result2Error(e));
    }
}
