package com.konstantin_romashenko.doyouknow.adapter;

public class ListItem
{
    private String text;
    private String cat;
    private int position;

    public String getCat()
    {
        return cat;
    }

    public void setCat(String cat)
    {
        this.cat = cat;
    }

    public String getText()
    {
        return text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setText(String text)
    {
        this.text = text;
    }


}
