package uk.ac.ucl.model;

public class Item
{
    private String type;
    private String value;

    public Item(String type, String value)
    {
        this.type = type;
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
