package com.example.p31;



public class Student {

    private int id;
    private String name;
    private String course;
    private int semester;

    public Student()
    {

    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setCourse(String course)
    {
        this.course=course;
    }
    public String getCourse()
    {
        return this.course;
    }
    public void setSemester(int semester)
    {
        this.semester=semester;
    }
    public int getSemester()
    {
        return this.semester;
    }
}
