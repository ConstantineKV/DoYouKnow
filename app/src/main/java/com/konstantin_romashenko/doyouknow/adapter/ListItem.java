package com.konstantin_romashenko.doyouknow.adapter;

public class ListItem
{
    private String text;
    private boolean favorite;
    private String cat;

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

    public boolean isFavorite()
    {
        return favorite;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setFavorite(boolean favorite)
    {
        this.favorite = favorite;
    }
}
