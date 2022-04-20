package domini;

public class ExcepcioDomini extends RuntimeException
{
    private final ErrorDomini error;

    public ExcepcioDomini(ErrorDomini error, String errorString)
    {
        super(errorString);
        this.error = error;
    }

    public ErrorDomini getError()
    {
        return error;
    }
}
