package org.pet.shop.exception;

public class PetShopApplicationException extends RuntimeException
{
    public PetShopApplicationException(final String message){super(message);}
    public PetShopApplicationException(final Throwable cause){super(cause);}
    public PetShopApplicationException(final String message, final Throwable cause){super(message, cause);}

}
