package ru.esstu.dto;

public enum Color {
    Blue,
    Red,
    Green,
    Yellow,
    Black,
    White,
    Orange,
    Purple,
    Brown,
    Pink,
    LightBlue,
    Gray,
    Beige,
    LightPurple,
    Turquoise,
    Peach,
    LightGreen,
    Burgundy,
    Cream;

    public static Color get(int index) {
        return Color.values()[index];
    }
    public static final int length = Color.values().length;
}
