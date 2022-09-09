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
    public byte primitiveByte = 1;
    public Byte objectByte = 2;
    public short primitiveShort = 3;
    public Short objectShort = 4;
    public int primitiveInt = 5;
    public Integer objectInteger = 6;
    public long primitiveLong = 7;
    public Long objectLong = 8L;
    public float primitiveFloat = 9.9F;
    public Float objectFloat = 10.10F;
    public double primitiveDouble = 11.11D;
    public Double objectDouble = 12.12D;
    public BigInteger bigInteger = new BigInteger("13");
    public BigDecimal bigDecimal = new BigDecimal("14.14");
    public boolean primitiveBoolean;
    public Boolean objectBoolean;
    public char primitiveChar = 'a';
    public Character objectCharacter = 'b';
    public String string = "cdefg";
    public Date date = new Date();
    public Calendar calendar = Calendar.getInstance();
    public TimeUnit enumType = TimeUnit.DAYS;
    //CHECKSTYLE:ON

}
