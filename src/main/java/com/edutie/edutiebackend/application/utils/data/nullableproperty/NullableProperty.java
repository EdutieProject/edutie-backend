package com.edutie.edutiebackend.application.utils.data.nullableproperty;

public class NullableProperty<TProperty> {
    TProperty property = null;
    public boolean overwrite = false;

    public NullableProperty()
    { }

    public NullableProperty(TProperty property)
    {
        this.property = property;
        overwrite = true;
    }

    public TProperty get() {
        return property;
    }
}