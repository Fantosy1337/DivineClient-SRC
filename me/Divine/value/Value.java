package me.Divine.value;

public class Value<T>
{
    public T value;
    private String name;
    private T defaultValue;
    
    public Value(final String name, final T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getDefaultValue() {
        return this.defaultValue;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public void setValue(final T value) {
        this.value = value;
    }
}
