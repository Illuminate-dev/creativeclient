package creativeclient.mod.impl.animationsmod.util;

import java.lang.reflect.*;

public class FieldWrapper<T>
{
    private static Field modifiersField;
    private Field field;
    
    public FieldWrapper(final String fieldName, final Class<?> clazz) {
        try {
            (this.field = clazz.getDeclaredField(fieldName)).setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException ex2) {
            final Exception ex = new Exception();
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public T get(final Object obj) {
        try {
            return (T)this.field.get(obj);
        }
        catch (IllegalArgumentException | IllegalAccessException ex2) {
            final Exception ex = new Exception();
            final Exception e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public void set(final Object obj, final T value) {
        try {
            this.field.set(obj, value);
        }
        catch (IllegalArgumentException | IllegalAccessException ex2) {
            final Exception ex = new Exception();
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public void setFinal(final Object obj, final T value) {
        try {
            FieldWrapper.modifiersField.setInt(this.field, this.field.getModifiers() & 0xFFFFFFEF);
            this.field.set(obj, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        try {
            (FieldWrapper.modifiersField = Field.class.getDeclaredField("modifiers")).setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException ex2) {
            final Exception ex = new Exception();
            final Exception e = ex;
            e.printStackTrace();
        }
    }
}
