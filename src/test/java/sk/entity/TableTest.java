package sk.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableTest
{
    @Test
    public void whenCreateNewTableThenIdGrow()
    {
        Table table1 = new Table();

        Table table2 = new Table();

        assertEquals(2, Table.ID);
    }
}