package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.simple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class SimpleDataTypes extends AValueObject {

    //CHECKSTYLE:OFF
    public final byte primitiveByte = 1;
    public final Byte objectByte = 2;
    public final short primitiveShort = 3;
    public final Short objectShort = 4;
    public final int primitiveInt = 5;
    public final Integer objectInteger = 6;
    public final long primitiveLong = 7;
    public final Long objectLong = 8L;
    public final float primitiveFloat = 9.9F;
    public final Float objectFloat = 10.10F;
    public final double primitiveDouble = 11.11D;
    public final Double objectDouble = 12.12D;
    public final BigInteger bigInteger = new BigInteger("13");
    public final BigDecimal bigDecimal = new BigDecimal("14.14");
    public boolean primitiveBoolean;
    public Boolean objectBoolean;
    public final char primitiveChar = 'a';
    public final Character objectCharacter = 'b';
    public final String string = "cdefg";
    public final Date date = new Date();
    public final Calendar calendar = Calendar.getInstance();
    public final TimeUnit enumType = TimeUnit.DAYS;
    //CHECKSTYLE:ON

}
